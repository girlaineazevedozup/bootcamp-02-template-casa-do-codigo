package com.zup.bootcamp.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public class PedidoTest {

    @DisplayName("Verifica se o total do pedido é igual ao passado como argumento")
    @ParameterizedTest
    @CsvSource({
            "10, true",
            "9.99, false",
            "10.01, false"
    })

    void teste1(BigDecimal valor, boolean resultadoEsperado) throws Exception{
        Autor autor = new Autor("autor", "autor@gmail.com", "descricao");
        Categoria categoria = new Categoria("categoria");
        Livro livro = new Livro("titulo", "resumo", "sumario", BigDecimal.TEN,
                100, "23232", LocalDate.of(2020, 01, 05),
                autor, categoria);
        Set<ItemPedido> itens = Set.of(new ItemPedido(livro, 1));
        Pedido pedido = new Pedido(Mockito.mock(Compra.class), BigDecimal.TEN, itens);

        Assertions.assertEquals(resultadoEsperado, pedido.totalIgual(valor));
    }
}
