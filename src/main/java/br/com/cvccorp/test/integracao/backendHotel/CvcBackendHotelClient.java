package br.com.cvccorp.test.integracao.backendHotel;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Interface cliente do Feing, realiza a consulta do links passados
 * pelo broker.
 *
 * @author Hugo Barros Camboim
 */
@FeignClient(name="CvcBackendHotelClient", url = "${cvccorp.url}")
public interface CvcBackendHotelClient {

    @Cacheable("cvcbackendhotel")
    @RequestMapping(method = RequestMethod.GET, path = "hotels/avail/{cityId}")
    List<CvcBackendHotelResponse> buscarTodosHoteisDaCidade(@PathVariable("cityId")Integer idCity);

    @Cacheable("cvcbackendhotel")
    @RequestMapping(method = RequestMethod.GET, path = "hotels/{hotelId}")
    List<CvcBackendHotelResponse> buscarHotel(@PathVariable("hotelId")Integer idHotel);
}
