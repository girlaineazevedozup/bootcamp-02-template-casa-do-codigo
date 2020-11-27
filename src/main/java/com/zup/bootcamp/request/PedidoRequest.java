package com.zup.bootcamp.request;

import com.zup.bootcamp.model.Compra;
import com.zup.bootcamp.model.ItemPedido;
import com.zup.bootcamp.model.Pedido;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PedidoRequest {

    @Positive
    @NotNull
    private BigDecimal total;

    @Size(min = 1)
    @Valid
    private List<PedidoItemRequest> itens = new ArrayList<>();

    public PedidoRequest(@Positive @NotNull BigDecimal total,
                         @Size(min = 1) @Valid List<PedidoItemRequest> itens) {
        super();
        this.total = total;
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "PedidoRequest{" +
                "total=" + total +
                ", itens=" + itens +
                '}';
    }

    public List<PedidoItemRequest> getItens() {
        return itens;
    }

    public Function<Compra, Pedido> toModel(EntityManager manager) {

        Set<ItemPedido> itensCalculados = itens.stream().map(item -> item.toModel(manager)).collect(Collectors.toSet());

        return(compra) -> {
            Pedido pedido = new Pedido(compra, total, itensCalculados);
            Assert.isTrue(pedido.totalIgual(total), "Total enviado não corresponde ao total real da compra.");
            return pedido;
        };
    }
}
