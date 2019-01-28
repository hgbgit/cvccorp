package br.com.cvccorp.test.web.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class ConsultaValorResponse {

    private String cityName;

    private Integer id;

    private List<Room> rooms;

    @Data
    public static class Room {

        private Integer roomID;

        private String categoryName;

        private PriceDetail priceDetail;

        private BigDecimal totalPrice;
    }
    @Data
    public static class PriceDetail {

        private BigDecimal pricePerDayAdult;

        private BigDecimal pricePerDayChild;
    }

}
