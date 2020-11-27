package com.zup.bootcamp.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.zup.bootcamp.model.Pedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonRootName("pedido")
public class PedidoResponse {

    @JsonProperty("total_pedido")
    private BigDecimal totalPedido;

    private List<PedidoItemResponse> itens = new ArrayList<>();

    public PedidoResponse(BigDecimal total, List<PedidoItemResponse> itens) {
        this.totalPedido = total;
        this.itens = itens;
    }

    public PedidoResponse(Pedido pedido) {
        totalPedido = pedido.getTotal();
        itens = pedido.getItens()
                .stream()
                .map(item -> new PedidoItemResponse(item.getLivro().getTitulo(),
                        item.getQuantidade(), item.getPrecoMomento()))
                .collect(Collectors.toList());
    }

    public BigDecimal getTotalPedido() {
        return totalPedido;
    }

    public List<PedidoItemResponse> getItens() {
        return itens;
    }
}
