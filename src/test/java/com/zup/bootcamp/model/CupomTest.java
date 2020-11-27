package com.zup.bootcamp.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

public class CupomTest {

    @ParameterizedTest
    @CsvSource({
        "0, true",
        "-1, false",
        "1, true",
        "-10, false"
    })

    @Test
    void teste1(long valor, boolean resultado) throws Exception{
        Cupom cupom = new Cupom("codigo", 10.00, LocalDate.now().plusDays(valor));
        Assertions.assertEquals(resultado, cupom.isValido());
    }
}
