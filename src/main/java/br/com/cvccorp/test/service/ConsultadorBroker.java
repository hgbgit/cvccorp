package br.com.cvccorp.test.service;

import br.com.cvccorp.test.web.dto.ConsultaValorCidadeRequest;
import br.com.cvccorp.test.web.dto.ConsultaValorHotelRequest;
import br.com.cvccorp.test.web.dto.ConsultaValorResponse;

import java.util.List;

/**
 * Interface para consultador broker. Deve ser implementada poro classes
 * que fazem comunicação por brokers.
 *
 * @author Hugo Barros Camboim
 */
public interface ConsultadorBroker {

    List<ConsultaValorResponse> consultarCidade(ConsultaValorCidadeRequest consultaValorCidadeRequest);

    List<ConsultaValorResponse> consultarHotel(ConsultaValorHotelRequest consultaValorHotelRequest);
}
