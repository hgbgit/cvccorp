package br.com.cvccorp.test.web;


import br.com.cvccorp.test.service.ConsultaViagemCalculoTotalService;
import br.com.cvccorp.test.web.dto.ConsultaValorCidadeRequest;
import br.com.cvccorp.test.web.dto.ConsultaValorHotelRequest;
import br.com.cvccorp.test.web.dto.ConsultaValorResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Implementa os Endpoints da aplicação local.
 * Traz os métodos da consulta por cidade e por hotel.
 *
 * @author Hugo Barros Camboim
 */
@RestController
@AllArgsConstructor
@RequestMapping("/consultar")
public class CheckinController {

    /** Service para o calculo de todos os brokers */
    private ConsultaViagemCalculoTotalService consultaViagemCalculoTotalService;

    /**
     * Faz a consulta por cidade e devolve a response no formato JSON. Realiza também
     * a validação dos dados passados como parâmetros na requisição.
     */
    @GetMapping("/cidade")
    public ResponseEntity<List<ConsultaValorResponse>> consultaValorCidade(
            @ModelAttribute @Valid ConsultaValorCidadeRequest consultaValorCidadeRequest){

        List<ConsultaValorResponse> response =  consultaViagemCalculoTotalService.consultarCidade(consultaValorCidadeRequest);

        return ResponseEntity.ok(response);
    }

    /**
     * Faz a consulta por hotel e devolve a response no formato JSON. Realiza também
     * a validação dos dados passados como parâmetros na requisição.
     */
    @GetMapping("/hotel")
    public ResponseEntity<List<ConsultaValorResponse>> consultaValorHotel(
            @ModelAttribute @Valid ConsultaValorHotelRequest consultaValorHotelRequest){

        List<ConsultaValorResponse> response =  consultaViagemCalculoTotalService.consultarHotel(consultaValorHotelRequest);

        return ResponseEntity.ok(response);
    }
}


