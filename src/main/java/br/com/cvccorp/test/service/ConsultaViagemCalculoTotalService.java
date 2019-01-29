package br.com.cvccorp.test.service;

import br.com.cvccorp.test.web.dto.ConsultaValorCidadeRequest;
import br.com.cvccorp.test.web.dto.ConsultaValorHotelRequest;
import br.com.cvccorp.test.web.dto.ConsultaValorResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Classe Service para consumo do serviço dos brokers, utiliza o
 * Request da busca por cidade e por hotel. Faz o calculo da proposta utilizando Streams
 * e por fim retorna os {@link ConsultaValorResponse}
 *
 * @author Hugo Barros Camboim
 */
@Service
@AllArgsConstructor
public class ConsultaViagemCalculoTotalService {

    /** List contendo instância de todos os consultadores de brokers */
    private List<ConsultadorBroker> consultadoresBroker;

    /** Instancia da calculadora para realizar o preço das propostas */
    private CalculadoraPrecoTotal calculadoraPrecoTotal;

    /**
     * Consulta todos os hoteis da uma cidade, cujo código é passado dentro do
     * {@link ConsultaValorCidadeRequest}. Faz o cálculo de todas as propostas
     * de hospedagem para todos os hotéis e acomodações.
     *
     * @param consultaValorCidadeRequest o request de proposta no formato da aplicação local
     * @return lista contendo todas as propostas para todos hotéis e acomodações da cidade
     * consultada nas datas informadas no request.
     */
    public List<ConsultaValorResponse> consultarCidade(ConsultaValorCidadeRequest consultaValorCidadeRequest){
        return consultaBase(consultaValorCidadeRequest.getQuantidadeDias(),
                consultaValorCidadeRequest.getQuantidadeDeAdulto(),
                consultaValorCidadeRequest.getQuantidadeDeCrianca(),
                broker -> broker.consultarCidade(consultaValorCidadeRequest));
    }

    /**
     * Consulta todos os hoteis da uma cidade, cujo código é passado dentro do
     * {@link ConsultaValorHotelRequest}. Faz o cálculo de todas as propostas
     * de hospedagem para todos os hotéis e acomodações.
     *
     * @param consultaValorHotelRequest o request de proposta no formato da aplicação local
     * @return lista contendo todas as propostas para todos hotéis e acomodações da cidade
     * consultada nas datas informadas no request.
     */
    public List<ConsultaValorResponse> consultarHotel(ConsultaValorHotelRequest consultaValorHotelRequest){
        return consultaBase(consultaValorHotelRequest.getQuantidadeDias(),
                consultaValorHotelRequest.getQuantidadeDeAdulto(),
                consultaValorHotelRequest.getQuantidadeDeCrianca(),
                broker -> broker.consultarHotel(consultaValorHotelRequest));
    }

    /** Implementa a consulta base dos 2 casos de uso uma vez que entre os dois casos muda apenas o "hotelID".
     * Evita duplicação de código.
     *
     * @param qntDias quantidade de dias da proposta de hospedagem
     * @param qntAdulto quantidade de adultos da proposta
     * @param qntCrianca quantidade de crianças da hospedagem
     * @param consultaTransformada consulta abstrata para mapeamento
     */
    private List<ConsultaValorResponse> consultaBase(long qntDias, int qntAdulto, int qntCrianca,
                                                     Function<ConsultadorBroker,List<ConsultaValorResponse>> consultaTransformada){
        return consultadoresBroker
                .parallelStream()
                .map(consultaTransformada)
                .flatMap(Collection::stream)
                .peek( proposta -> this.calcularPrecoDaProposta( proposta,
                        qntDias,
                        qntAdulto,
                        qntCrianca))
                .collect(Collectors.toList());
    }

    /** Utiliza a calculadora para calculo do preço total, com base na quantidade de dias x adultos e crianças
     * e atribui o valor total na proposta da reserva.
     *
     * @param propostaDeReserva a proposta na qual será atribuío o valor total
     * @param qntDias numero de dias da hospedagem
     * @param qntAdulto numero de adultos que serão hospedados
     * @param qntCrianca numero de crianças da hospedagem
     *
     */
    private void calcularPrecoDaProposta(ConsultaValorResponse propostaDeReserva, long qntDias, int qntAdulto, int qntCrianca){
        propostaDeReserva.getRooms().forEach(room -> {
            BigDecimal valorTotalDoQuarto = calculadoraPrecoTotal.calcular(room.getPriceDetail(), qntDias,qntAdulto,qntCrianca);             ;
            room.setTotalPrice(valorTotalDoQuarto);
        });
    }


}
