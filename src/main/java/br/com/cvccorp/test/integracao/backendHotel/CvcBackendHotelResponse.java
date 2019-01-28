package br.com.cvccorp.test.integracao.backendHotel;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
public class CvcBackendHotelResponse {

    private String cityCode;

    private String cityName;

    private Integer id;

    private String name;

    private List<Room> rooms;

    @Data
    public static class Room {

        private Integer roomID;

        private String categoryName;

        private Price price;

    }
    @Data
    public static class Price {

        private BigDecimal adult;

        private BigDecimal child;
    }
}
