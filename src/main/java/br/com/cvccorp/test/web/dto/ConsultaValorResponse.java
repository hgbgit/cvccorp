package br.com.cvccorp.test.web.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** Representa um response de acordo com os requisitos da aplicação local.
 *
 * Ex.:
 * {
 *   "id": 1,
 *   "cityName": "Porto Seguro",
 *   "rooms":[{
 *       "roomID": 1,
 *       "categoryName": "Standard",
 *       "totalPrice": 10000.00,
 *       "priceDetail": {
 *         "pricePerDayAdult": 500.00,
 *         "pricePerDayChild": 50.00
 *       }
 *     }]
 * }
 *
 * @author Hugo Barros Camboim
 * */
@Data
@ToString
public class ConsultaValorResponse {

    /** Nome da Cidade */
    private String cityName;

    /** Id do hotel */
    private Integer id;

    /** Lista de acomodações */
    private List<Room> rooms;

    /** Representa as acomodações de acordo com os requisitos da aplicação local */
    @Data
    public static class Room {

        /** Id da acomodação */
        private Integer roomID;

        /** Categoria da acomodação */
        private String categoryName;

        /** Detalhe de preço da acomodação */
        private PriceDetail priceDetail;

        /** Preço total da proposta */
        private BigDecimal totalPrice;
    }
    /** Representa o detalhe de preço de uma acomodação */
    @Data
    public static class PriceDetail {

        /** Preço por adulto */
        private BigDecimal pricePerDayAdult;

        /** Preço por criança */
        private BigDecimal pricePerDayChild;
    }

}
