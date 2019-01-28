package br.com.cvccorp.test.web.dto;

import br.com.cvccorp.test.integracao.backendHotel.CvcBackendHotelResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/** Classe Mapper que faz o mapeamento de uma response da aplicação externa para a aplicação local.
 *
 * @author Hugo Barros Camboim
 */
@Mapper
public interface ConsultaValorResponseMapper {

    ConsultaValorResponseMapper INSTANCE = Mappers.getMapper( ConsultaValorResponseMapper.class );

    ConsultaValorResponse backendHotelToConsultaValor(CvcBackendHotelResponse source);

    List<ConsultaValorResponse> backendHotelToConsultaValores(List<CvcBackendHotelResponse> source);

    @Mappings({
            @Mapping(source = "price", target = "priceDetail"),
            @Mapping(target = "totalPrice", ignore = true)

    })
    ConsultaValorResponse.Room backendRoomToRoom(CvcBackendHotelResponse.Room room);

    @Mappings({
            @Mapping(source = "adult",target = "pricePerDayAdult"),
            @Mapping(source = "child",target = "pricePerDayChild")
    })
    ConsultaValorResponse.PriceDetail priceToDetail(CvcBackendHotelResponse.Price price);

}
