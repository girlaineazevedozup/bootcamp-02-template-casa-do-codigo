package com.zup.bootcamp.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Valid
    @OneToOne
    private Compra compra;

    @Positive
    @NotNull
    private BigDecimal total;

    @Size(min = 1)
    @ElementCollection
    private Set<ItemPedido> itens = new HashSet<>();

    @Deprecated
    public Pedido() {
    }

    public Pedido(@NotNull @Valid Compra compra, @Positive @NotNull BigDecimal total,
                  @Size(min = 1) Set<ItemPedido> itens) {
        this.compra = compra;
        this.total = total;
        this.itens.addAll(itens);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "total=" + total +
                ", itens=" + itens +
                '}';
    }

    public boolean totalIgual(@Positive @NotNull BigDecimal total) {
        BigDecimal totalPedido = itens.stream().map(ItemPedido :: total).reduce(BigDecimal.ZERO,
                (atual, proximo) -> atual.add(proximo));

        return totalPedido.compareTo(total) == 0;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }
}
