package br.com.cvccorp.test.web.dto;

import br.com.cvccorp.test.util.DateUtil;

import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Representa um request da aplicação local na consulta de um hotel.
 *
 * @author Hugo Barros Camboim
 */
@Data
public class ConsultaValorHotelRequest {

    /** Id do hotel no qual deve ser feita a busca */
    @NotNull
    private Integer hotelId;

    /** String que representa a data de check-in no formato: dd/MM/yyyy */
    @NotNull
    private String checkin;

    /** String que representa a data de check-out no formato: dd/MM/yyyy */
    @NotNull
    private String checkout;

    /** Quantidade de adultos a serem hospedados */
    @Min(0)
    private Integer quantidadeDeAdulto;

    /** Quantidade de crianças a serem hospedadas */
    @Min(0)
    private Integer quantidadeDeCrianca;

    public ConsultaValorHotelRequest(){}

    /** Retorna a quantidade de dias da hospedagem com base nas string de datas
     * da instância
     *
     * @return numero de dias total da hospedagem
     */
    public long getQuantidadeDias(){
        return DateUtil.getQuantidadeDias(this.checkin,this.checkout);
    }

}
