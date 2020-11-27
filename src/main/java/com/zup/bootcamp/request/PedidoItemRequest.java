package com.zup.bootcamp.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.bootcamp.model.ItemPedido;
import com.zup.bootcamp.model.Livro;
import com.zup.bootcamp.validation.annotation.ExistsValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PedidoItemRequest {

    @NotNull
    @ExistsValue(domainClass = Livro.class, fieldName = "id")
    @JsonProperty("id_livro")
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

    public ItemPedido toModel(EntityManager manager) {
        @NotNull Livro livro = manager.find(Livro.class, idLivro);
        return new ItemPedido(livro, quantidade);
    }
}
