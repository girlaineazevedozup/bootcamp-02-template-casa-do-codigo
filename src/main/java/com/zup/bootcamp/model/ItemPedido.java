package com.zup.bootcamp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
@JsonRootName("item_pedido")
public class ItemPedido {

    @ManyToOne
    @NotNull
    private Livro livro;

    @Positive
    private int quantidade;

    @Positive
    @JsonProperty("preco_momento")
    private BigDecimal precoMomento;

    @Deprecated
    public ItemPedido(){
    }
    public ItemPedido(@NotNull Livro livro, @Positive int quantidade) {
        this.livro = livro;
        this.quantidade = quantidade;
        this.precoMomento = livro.getPreco();
    }

    public BigDecimal total() {
        return precoMomento.multiply(new BigDecimal(quantidade));
    }

    @Override
    public String toString() {
        return "item_pedido{" +
                "livro=" + livro +
                ", quantidade=" + quantidade +
                ", preco_momento=" + precoMomento +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return livro.equals(that.livro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livro);
    }

    public Livro getLivro() {
        return livro;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoMomento() {
        return precoMomento;
    }
}
