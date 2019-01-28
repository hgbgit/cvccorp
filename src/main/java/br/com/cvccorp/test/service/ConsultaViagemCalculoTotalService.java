package br.com.cvccorp.test.service;

import br.com.cvccorp.test.web.dto.ConsultaValorCidadeRequest;
import br.com.cvccorp.test.web.dto.ConsultaValorHotelRequest;
import br.com.cvccorp.test.web.dto.ConsultaValorResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConsultaViagemCalculoTotalService {

    private List<ConsultadorBroker> consultadoresBroker;

    private CalculadoraPrecoTotal calculadoraPrecoTotal;

    public List<ConsultaValorResponse> consultarCidade(ConsultaValorCidadeRequest consultaValorCidadeRequest){
        return consultadoresBroker
                .parallelStream()
                .map( broker -> broker.consultarCidade(consultaValorCidadeRequest))
                .flatMap(Collection::stream)
                .peek( proposta -> this.calcularPrecoDaProposta( proposta,
                        consultaValorCidadeRequest.getQuantidadeDias(),
                        consultaValorCidadeRequest.getQuantidadeDeAdulto(),
                        consultaValorCidadeRequest.getQuantidadeDeCrianca()))
                .collect(Collectors.toList());
    }

    public List<ConsultaValorResponse> consultarHotel(ConsultaValorHotelRequest consultaValorHotelRequest){
        return consultadoresBroker
                .parallelStream()
                .map( broker -> broker.consultarHotel(consultaValorHotelRequest))
                .flatMap(Collection::stream)
                .peek( proposta -> this.calcularPrecoDaProposta( proposta,
                        consultaValorHotelRequest.getQuantidadeDias(),
                        consultaValorHotelRequest.getQuantidadeDeAdulto(),
                        consultaValorHotelRequest.getQuantidadeDeCrianca()))
                .collect(Collectors.toList());
    }


    private void calcularPrecoDaProposta(ConsultaValorResponse propostaDeReserva, long qntDias, int qntAdulto, int qntCrianca){
        propostaDeReserva.getRooms().forEach(room -> {
            BigDecimal valorTotalDoQuarto = calculadoraPrecoTotal.calcular(room.getPriceDetail(), qntDias,qntAdulto,qntCrianca);             ;
            room.setTotalPrice(valorTotalDoQuarto);
        });
    }

}
