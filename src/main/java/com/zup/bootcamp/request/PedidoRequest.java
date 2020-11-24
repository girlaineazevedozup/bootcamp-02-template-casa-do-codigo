package com.zup.bootcamp.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
}
