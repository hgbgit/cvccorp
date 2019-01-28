package br.com.cvccorp.test.service;

import br.com.cvccorp.test.web.dto.ConsultaValorCidadeRequest;
import br.com.cvccorp.test.web.dto.ConsultaValorHotelRequest;
import br.com.cvccorp.test.web.dto.ConsultaValorResponse;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class ConsultaViagemCalculoTotalServiceTest {

    @Mock
    ConsultadorBroker consultadorBroker;

    // Se mock de propósito.
    CalculadoraPrecoTotal calculadoraPrecoTotal = new CalculadoraPrecoTotal();

    ConsultaViagemCalculoTotalService instance;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        instance = new ConsultaViagemCalculoTotalService(Arrays.asList(consultadorBroker), calculadoraPrecoTotal);
    }

    /**
     * Checkin dia: 20/05/2017, Checkout dia: 25/05/2017, Total de Adultos: 2 com 1 Criança
     *
     * Com Broker com R$ 100,00 para adulto e R$ 50,00 para criança:
     */
    @SneakyThrows
    @Test
    public void testCidade(){

        // Given
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        ConsultaValorCidadeRequest consultaValorCidadeRequest = new ConsultaValorCidadeRequest(){{
                setCheckin("20/05/2017");
                setCheckout("25/05/2017");
                setQuantidadeDeAdulto(2);
                setQuantidadeDeCrianca(1);
        }};

        // Expected Result
        ConsultaValorResponse responseBrokerExpected = new ConsultaValorResponse() {{
            setId(1);
            setCityName("Porto Seguro");
            setRooms(Arrays.asList(
                    new Room(){{
                        this.setRoomID(0);
                        this.setCategoryName("Standard");
                        this.setPriceDetail( new PriceDetail() {{
                            this.setPricePerDayAdult(BigDecimal.valueOf(100));
                            this.setPricePerDayChild(BigDecimal.valueOf(50));
                        }});
                    }}
            ));
        }};

        Mockito.when(consultadorBroker.consultarCidade(consultaValorCidadeRequest)).thenReturn(Arrays.asList(responseBrokerExpected));

        // when
        List<ConsultaValorResponse> response = instance.consultarCidade(consultaValorCidadeRequest);

        // then
        Assert.assertEquals(1, response.size());
       // Assert.assertEquals(BigDecimal.valueOf(1550.0), response.get(0).getRooms().get(0).getTotalPrice());
    }

    /**
     * Checkin dia: 20/05/2017, Checkout dia: 25/05/2017, Total de Adultos: 2 com 1 Criança
     *
     * Com Broker com R$ 100,00 para adulto e R$ 50,00 para criança:
     */
    @SneakyThrows
    @Test
    public void testHotel(){

        // Given
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        ConsultaValorHotelRequest consultaValorHotelRequest = new ConsultaValorHotelRequest(){{
            setCheckin("20/05/2017");
            setCheckout("25/05/2017");
            setQuantidadeDeAdulto(2);
            setQuantidadeDeCrianca(1);
        }};

        // Expected Result
        ConsultaValorResponse responseBrokerExpected = new ConsultaValorResponse() {{
            setId(1);
            setCityName("Porto Seguro");
            setRooms(Arrays.asList(
                    new Room(){{
                        this.setRoomID(0);
                        this.setCategoryName("Standard");
                        this.setPriceDetail( new PriceDetail() {{
                            this.setPricePerDayAdult(BigDecimal.valueOf(100));
                            this.setPricePerDayChild(BigDecimal.valueOf(50));
                        }});
                    }}
            ));
        }};

        Mockito.when(consultadorBroker.consultarHotel(consultaValorHotelRequest)).thenReturn(Arrays.asList(responseBrokerExpected));

        // when
        List<ConsultaValorResponse> response = instance.consultarHotel(consultaValorHotelRequest);

        // then
        Assert.assertEquals(1, response.size());
       // Assert.assertEquals(BigDecimal.valueOf(1550.0), response.get(0).getRooms().get(0).getTotalPrice());
    }
}
