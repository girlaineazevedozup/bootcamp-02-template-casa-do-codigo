package com.zup.bootcamp.response;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.zup.bootcamp.model.ItemPedido;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Set;

@JsonRootName("itens")
public class PedidoItemResponse {

    private String titulo;

    private int quantidade;

    private BigDecimal preco;

    public PedidoItemResponse(String titulo, int quantidade, BigDecimal preco) {
        this.titulo = titulo;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }
}
