package br.com.cvccorp.test.integracao.backendHotel;

import br.com.cvccorp.test.service.ConsultadorBroker;
import br.com.cvccorp.test.web.dto.ConsultaValorCidadeRequest;
import br.com.cvccorp.test.web.dto.ConsultaValorHotelRequest;
import br.com.cvccorp.test.web.dto.ConsultaValorResponse;
import br.com.cvccorp.test.web.dto.ConsultaValorResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CvcBackendHotelProxyService implements ConsultadorBroker {

    private CvcBackendHotelClient cvcBackendHotelClient;

    public List<ConsultaValorResponse> consultarCidade(ConsultaValorCidadeRequest consultaValorCidadeRequest){

        List<CvcBackendHotelResponse> hoteis = cvcBackendHotelClient.buscarTodosHoteisDaCidade(consultaValorCidadeRequest.getCityCode());

        List<ConsultaValorResponse> response =  ConsultaValorResponseMapper.INSTANCE.backendHotelToConsultaValores(hoteis);

        return response;
    }

    public List<ConsultaValorResponse> consultarHotel(ConsultaValorHotelRequest consultaValorHotelRequest){

        List<CvcBackendHotelResponse> hoteis = cvcBackendHotelClient.buscarHotel(consultaValorHotelRequest.getHotelId());

        List<ConsultaValorResponse> response =  ConsultaValorResponseMapper.INSTANCE.backendHotelToConsultaValores(hoteis);

        return response;
    }
}
