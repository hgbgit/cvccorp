package br.com.cvccorp.test.web;


import br.com.cvccorp.test.service.ConsultaViagemCalculoTotalService;
import br.com.cvccorp.test.web.dto.ConsultaValorCidadeRequest;
import br.com.cvccorp.test.web.dto.ConsultaValorHotelRequest;
import br.com.cvccorp.test.web.dto.ConsultaValorResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class CheckinController {

    private ConsultaViagemCalculoTotalService consultaViagemCalculoTotalService;

    @GetMapping("/consultar/cidade")
    public ResponseEntity<List<ConsultaValorResponse>> consultaValorCidade(
            @ModelAttribute @Valid ConsultaValorCidadeRequest consultaValorCidadeRequest){

        List<ConsultaValorResponse> response =  consultaViagemCalculoTotalService.consultarCidade(consultaValorCidadeRequest);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/consultar/hotel")
    public ResponseEntity<List<ConsultaValorResponse>> consultaValorHotel(
            @ModelAttribute @Valid ConsultaValorHotelRequest consultaValorHotelRequest){

        List<ConsultaValorResponse> response =  consultaViagemCalculoTotalService.consultarHotel(consultaValorHotelRequest);

        return ResponseEntity.ok(response);
    }
}


