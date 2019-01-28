package br.com.cvccorp.test.service;

import br.com.cvccorp.test.web.dto.ConsultaValorCidadeRequest;
import br.com.cvccorp.test.web.dto.ConsultaValorHotelRequest;
import br.com.cvccorp.test.web.dto.ConsultaValorResponse;

import java.util.List;


public interface ConsultadorBroker {

    List<ConsultaValorResponse> consultarCidade(ConsultaValorCidadeRequest consultaValorCidadeRequest);

    List<ConsultaValorResponse> consultarHotel(ConsultaValorHotelRequest consultaValorHotelRequest);
}
