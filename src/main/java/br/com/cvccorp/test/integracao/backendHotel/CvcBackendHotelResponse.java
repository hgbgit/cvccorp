package br.com.cvccorp.test.integracao.backendHotel;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * Representa um reponse(JSON) no formato da aplicação do broker.
 * Utiliza o Lombok, para geração de getters/setters e contrutor.
 *
 * Ex.:
 *[{
 *   "id": 1,
 *   "name": "Hotel Teste 1",
 *   "cityCode": 1032,
 *   "cityName": "Porto Seguro",
 *   "rooms": [
 *     {
 *       "roomID": 0,
 *       "categoryName": "Standard",
 *       "price": {
 *         "adult": 1372.54,
 *         "child": 848.61
 *       }
 *     }
 *   ]
 * }]
 *
 * @author Hugo Barros Camboim
 */
@Data
@ToString
public class CvcBackendHotelResponse {

    /** Código da Cidade */
    private String cityCode;

    /** Nome da Cidade */
    private String cityName;

    /** Id do Hotel */
    private Integer id;

    /** Nome do Hotel */
    private String name;

    /** Lista de acomodações disponíveis no hotel */
    private List<Room> rooms;

    /** Representa uma acomodação de acordo com o formato da aplicação do broker */
    @Data
    public static class Room {

        /** Id da acomodação */
        private Integer roomID;

        /** Categoria da acomodação */
        private String categoryName;

        /** Preço da acomodação */
        private Price price;

    }
    /** Representa o preço da diária da uma acomodação por contendo valor de adulto e criança */
    @Data
    public static class Price {

        /** Valor da diária para adulto */
        private BigDecimal adult;

        /** valor da diária para criança */
        private BigDecimal child;
    }
}
