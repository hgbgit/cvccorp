
package br.com.cvccorp.test.integracao.backendHotel;

import br.com.cvccorp.test.service.ConsultadorBroker;
import br.com.cvccorp.test.web.dto.ConsultaValorCidadeRequest;
import br.com.cvccorp.test.web.dto.ConsultaValorHotelRequest;
import br.com.cvccorp.test.web.dto.ConsultaValorResponse;
import br.com.cvccorp.test.web.dto.ConsultaValorResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Classe Proxy que consume o CvcBackendHotelClient,
 * traz os resultados no formato de CvcBackendHotelResponse e faz um mapeamento
 * para ConsultaValorResponse.
 *
 * @author Hugo Barros Camboim
 */
@Component
@AllArgsConstructor
public class CvcBackendHotelProxyService implements ConsultadorBroker {

    private CvcBackendHotelClient cvcBackendHotelClient;

    /**
     * Realiza a consulta de Hotéis por cidade utilizando o Feing cliente e converte o resultado
     * para o formato da resposta da aplicação local.
     *
     * @param consultaValorCidadeRequest o request no formato da aplicação local
     * @return a resposta no formato da aplicação local
     */
    public List<ConsultaValorResponse> consultarCidade(ConsultaValorCidadeRequest consultaValorCidadeRequest){

        List<CvcBackendHotelResponse> hoteis = cvcBackendHotelClient.buscarTodosHoteisDaCidade(consultaValorCidadeRequest.getCityCode());

        List<ConsultaValorResponse> response =  ConsultaValorResponseMapper.INSTANCE.backendHotelToConsultaValores(hoteis);

        return response;
    }

    /**
     * Realiza a consulta de um Hotel usando o Feing cliente e converte o resultado
     * para o formato da resposta da aplicação local.
     *
     * @param consultaValorHotelRequest o request no formato da aplicação local
     * @return a resposta no formato da aplicação local
     */
    public List<ConsultaValorResponse> consultarHotel(ConsultaValorHotelRequest consultaValorHotelRequest){

        List<CvcBackendHotelResponse> hoteis = cvcBackendHotelClient.buscarHotel(consultaValorHotelRequest.getHotelId());

        List<ConsultaValorResponse> response =  ConsultaValorResponseMapper.INSTANCE.backendHotelToConsultaValores(hoteis);

        return response;
    }
}
