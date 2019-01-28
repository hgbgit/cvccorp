package br.com.cvccorp.test.service;

import br.com.cvccorp.test.web.dto.ConsultaValorResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 *  Realiza o cálculo de acordo com a regra informada nos requisitos.
 *  Basicamente multiplica quantidade de adultos por quantidade de dias e
 *  adiciona 30% da comissão. Faz o mesmo para a quantidade de crianças informadas.
 *
 * @author Hugo Barros Camboim
 * */
@Component
@AllArgsConstructor
public class CalculadoraPrecoTotal {

    /**
     * Calcula o valor total da proposta de hospedagem de acordo, com o preço por adulto,
     * preço por criança x quantidade de dias.
     *
     * @param precoDetail Detalhe de preço da acomodação.
     * @param quantidadeDeDias quantidade de dias da hospedagem
     * @param quantidadeAdulto quantidade de adultos da proposta
     * @param quantidadeCrianca quantidade de crianças da proposta
     *
     * @return o valor total calculado de acordo com a regra.
     */
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
        // 30% = valor x 30 / 100 = valor * 0.3 formula do README um pouco confusa, adotando regra matemática
        BigDecimal comissao = valorAdulto.multiply(BigDecimal.valueOf(0.3)); //30% valorAdulto
        comissao.add(valorCrianca.multiply(BigDecimal.valueOf(0.3)));//30% valorCriança

        return valorAdulto.add(valorCrianca).add(comissao);
    }
}
