package br.com.cvccorp.test.web.dto;

import br.com.cvccorp.test.util.DateUtil;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Representa um request da aplicação local na consulta de hotéis por cidade.
 *
 * @author Hugo Barros Camboim
 */
@Data
public class ConsultaValorCidadeRequest {

    /** Código da cidade */
    @NotNull
    private Integer cityCode;

    /** String que representa a data de check-in no formato: dd/MM/yyyy */
    @NotNull
    private String checkin;

    /** Sting que representa a data de check-out no formato: dd/MM/yyyy */
    @NotNull
    private String checkout;

    /** Quantidade de adultos a serem hospedados */
    @Min(0)
    private Integer quantidadeDeAdulto;

    /** Quantidade de crianças a serem hospedadas */
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
