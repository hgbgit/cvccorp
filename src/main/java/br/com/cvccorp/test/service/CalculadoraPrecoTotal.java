package br.com.cvccorp.test.service;

import br.com.cvccorp.test.web.dto.ConsultaValorCidadeRequest;
import br.com.cvccorp.test.web.dto.ConsultaValorResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@AllArgsConstructor
public class CalculadoraPrecoTotal {

    public BigDecimal calcular(ConsultaValorResponse.PriceDetail precoDetail, long quantidadeDeDias, int quantidadeAdulto,
                               int quantidadeCrianca){

        BigDecimal valorAdulto = precoDetail.getPricePerDayAdult()
                                            .multiply(BigDecimal.valueOf(quantidadeDeDias))
                                            .multiply(BigDecimal.valueOf(quantidadeAdulto));
        BigDecimal valorCrianca = precoDetail.getPricePerDayChild()
                                            .multiply(BigDecimal.valueOf(quantidadeDeDias))
                                            .multiply(BigDecimal.valueOf(quantidadeCrianca));


        // Regra não tá clara
        //Adicionar 30% de comissão para adulto e para criança * Formula para fazer isso ({valorViagemAdulto}/0.7) Ex: (500/0.7);
        // 30% = valor x 30 / 100 -> formula do README um pouco confusa
        BigDecimal comissao = valorAdulto.multiply(BigDecimal.valueOf(30))
                .divide(BigDecimal.valueOf(100),  2, RoundingMode.HALF_UP); //30% valorAdulto
        comissao.add(valorCrianca.multiply(BigDecimal.valueOf(30))//30% valorCriança
                .divide(BigDecimal.valueOf(100),  2, RoundingMode.HALF_UP));

        return valorAdulto.add(valorCrianca).add(comissao);
    }
}
