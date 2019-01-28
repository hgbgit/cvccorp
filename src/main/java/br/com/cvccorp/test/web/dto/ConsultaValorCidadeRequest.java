package br.com.cvccorp.test.web.dto;

import br.com.cvccorp.test.util.DateUtil;
import br.com.cvccorp.test.util.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


/**
 * CityCode
 * Checkin
 * Checkout
 * Quantidade de Adultos
 * Quantidade de Crianças
 */
@Data
public class ConsultaValorCidadeRequest {

    @NotNull
    private Integer cityCode;

    @NotNull
    private String checkin;

    @NotNull
    private String checkout;

    @Min(0)
    private Integer quantidadeDeAdulto;

    @Min(0)
    private Integer quantidadeDeCrianca;

    public ConsultaValorCidadeRequest(){}

    /**
     * Calcula a quantidade de dias entra as datas de checkin e checkout
     *
     * @return quantidade de dias no formato inteiro
     */
    public long getQuantidadeDias(){
        return DateUtil.getQuantidadeDias(this.checkin,this.checkout);
    }
}
