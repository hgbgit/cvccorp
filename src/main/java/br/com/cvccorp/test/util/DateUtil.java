package br.com.cvccorp.test.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static Date toDate(String data){
        try {
            return dateFormat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Date();
    }

    /**
     * Calcula a quantidade de dias entre as datas de checkin e checkout
     *
     * @return quantidade de dias no formato inteiro
     */
    public static long getQuantidadeDias(String inicio, String fim){

        LocalDate dataInicio = DateUtil.toDate(inicio)
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dataFim = DateUtil.toDate(fim)
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long qntDias = Duration.between(dataInicio.atStartOfDay(), dataFim.atStartOfDay()).toDays();

        return qntDias;
    }
}
