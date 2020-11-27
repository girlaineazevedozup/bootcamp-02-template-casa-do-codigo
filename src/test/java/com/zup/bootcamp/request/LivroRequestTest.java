package com.zup.bootcamp.request;

import com.zup.bootcamp.model.Autor;
import com.zup.bootcamp.model.Categoria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class LivroRequestTest {

    private LivroRequest request = new LivroRequest("", "", "",
            BigDecimal.TEN, 100, "", 1l, 1l);

    @Test
    @DisplayName("Cria o livro com categoria e autor cadastrados")
    void teste1() throws Exception{
        EntityManager manager = Mockito.mock(EntityManager.class);

        Mockito.when(manager.find(Categoria.class, 1l))
                .thenReturn(new Categoria(""));

        Mockito.when(manager.find(Autor.class, 1l))
                .thenReturn(new Autor("", "", ""));

        Assertions.assertNotNull(request.toModel(manager));
    }
    @Test
    @DisplayName("Não cria se o autor não existir no banco")
    void teste2() throws Exception{
        EntityManager manager = Mockito.mock(EntityManager.class);

        Mockito.when(manager.find(Categoria.class, 1l))
                .thenReturn(new Categoria(""));

        Mockito.when(manager.find(Autor.class, 1l))
                .thenReturn(null);

        Assertions.assertThrows(IllegalStateException.class, () -> {
            request.toModel(manager);
        });
    }

    @Test
    @DisplayName("Não cria se a categoria não existir no banco")
    void teste3() throws Exception{
        EntityManager manager = Mockito.mock(EntityManager.class);

        Mockito.when(manager.find(Categoria.class, 1l))
                .thenReturn(null);

        Mockito.when(manager.find(Autor.class, 1l))
                .thenReturn(new Autor("", "", ""));

        Assertions.assertThrows(IllegalStateException.class, () -> {
            request.toModel(manager);
        });
    }
}
