package com.zup.bootcamp.request;

import com.zup.bootcamp.infrastructure.CupomRepository;
import com.zup.bootcamp.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CompraRequestTest {

    private static final String CODIGO_CUPOM = "codigo_cupom";
    
    private EntityManager manager = Mockito.mock(EntityManager.class);
    private CupomRepository cupomRepository = Mockito.mock(CupomRepository.class);
    private List<PedidoItemRequest> itens = List.of(new PedidoItemRequest(1l, 1));
    private PedidoRequest pedidoRequest = new PedidoRequest(new BigDecimal("190"), itens);
    private Pais pais = new Pais("Brasil");
    private Estado estado = new Estado("rio", pais);
    private Autor autor = new Autor("Jose", "jose@gmail.com", "autor do livro");
    private Categoria categoria = new Categoria("Desenvolvimento");
    private Livro livro = new Livro("Testes", "livro de testes", "sumario",
            new BigDecimal("190"),400, "68758", LocalDate.of(2020, 01, 05),
            autor, categoria);

    {
        Mockito.when(manager.find(Pais.class, 1l)).thenReturn(pais);
        Mockito.when(manager.find(Livro.class, 1l)).thenReturn(livro);
        Mockito.when(manager.find(Estado.class, 1l)).thenReturn(estado);
        Mockito.when(cupomRepository.findByCodigo(CODIGO_CUPOM)).thenReturn(
                new Cupom(CODIGO_CUPOM, 10.00, LocalDate.now().plusDays(1l)));
    }

    private CompraRequest request = new CompraRequest("gi@gmail.com", "Girlaine",
            "Neves", "56841714008", "endereco", "complemento",
            "rio de janeiro", 1l, "1111111", "123456", pedidoRequest);

    @Test
    @DisplayName("Cria compra com estado e cupom")
    void teste1() throws Exception{
        request.setCodigoCupom(CODIGO_CUPOM);
        request.setIdEstado(1l);
        Compra compra = request.toModel(manager, cupomRepository);

        //usaria pra saber se entrou no if e preencheu a compra se a Class compra não tivesse os gets.
        //Assertions.assertNull(compra);
        Mockito.verify(manager).find(Estado.class, 1l);
        Mockito.verify(cupomRepository).findByCodigo(CODIGO_CUPOM);
    }

    @Test
    @DisplayName("Cria compra sem estado e com cupom")
    void teste2() throws Exception{
        request.setCodigoCupom(CODIGO_CUPOM);
        Compra compra = request.toModel(manager, cupomRepository);

        Mockito.verify(manager, Mockito.never()).find(Mockito.eq(Estado.class), Mockito.anyLong());
        Mockito.verify(cupomRepository).findByCodigo(CODIGO_CUPOM);
    }

    @Test
    @DisplayName("Cria compra sem estado e sem cupom")
    void teste3() throws Exception{
        Compra compra = request.toModel(manager, cupomRepository);

        Mockito.verify(manager, Mockito.never()).find(Mockito.eq(Estado.class), Mockito.anyLong());
        Mockito.verify(cupomRepository, Mockito.never()).findByCodigo(Mockito.anyString());
    }

    @ParameterizedTest
    @DisplayName("Valida documento(CPF/CNPJ)")
    @CsvSource({
            "56841714008, true",
            "03890547000106, true",
            "123456789, false"
    })
    void teste4(String documento, boolean resultadoEsperado) throws Exception{
        CompraRequest request = new CompraRequest("gi@gmail.com", "Girlaine",
                "Neves", documento, "endereco", "complemento",
                "rio de janeiro", 1l, "1111111", "123456", pedidoRequest);

        Assertions.assertEquals(resultadoEsperado, request.documentoValido());
    }

}
