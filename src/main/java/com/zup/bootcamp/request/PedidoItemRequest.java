package com.zup.bootcamp.request;

import com.zup.bootcamp.model.Livro;
import com.zup.bootcamp.validation.annotation.ExistsId;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PedidoItemRequest {

    @NotNull
    @ExistsId(domainClass = Livro.class, fieldName = "id")
    private Long idLivro;

    @Positive
    private int quantidade;

    public PedidoItemRequest(@NotNull Long idLivro, @Positive int quantidade) {
        super();
        this.idLivro = idLivro;
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "PedidoItemRequest{" +
                "idLivro=" + idLivro +
                ", quantidade=" + quantidade +
                '}';
    }

    public Long getIdLivro() {
        return idLivro;
    }
}
