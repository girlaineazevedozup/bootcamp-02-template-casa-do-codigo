package com.zup.bootcamp.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.bootcamp.model.Compra;
import com.zup.bootcamp.model.CupomAplicado;

import java.math.BigDecimal;

public class DetalheCompraResponse {

    private String email;

    private String nome;

    private String sobrenome;

    private String documento;

    private String endereco;

    private String complemento;

    private String pais;

    private String telefone;

    private String cep;

    private String estado;

    private PedidoResponse pedido;

    @JsonProperty("total_compra")
    private BigDecimal totalCompra;

    @JsonProperty("existe_cupom")
    private boolean existeCupom;

    @JsonProperty("valor_cupom")
    private Double valorCupom;

    public DetalheCompraResponse(Compra compra) {
        this.email = compra.getEmail();
        this.nome = compra.getNome();
        this.sobrenome = compra.getSobrenome();
        this.documento = compra.getDocumento();
        this.endereco = compra.getEndereco();
        this.complemento = compra.getComplemento();
        this.pais = compra.getPais().getNome();
        this.telefone = compra.getTelefone();
        this.cep = compra.getCep();
        this.pedido = new PedidoResponse(compra.getPedido());
        this.totalCompra = compra.getPedido().getTotal();

        if(compra.temEstado())
            this.estado = compra.getEstado().getNome();

        if(compra.temCupomAplicado()){
            this.existeCupom = true;
            this.totalCompra = calculaTotalComDesconto(compra.getCupomAplicado());
            this.valorCupom = compra.getCupomAplicado().getPercentualDescontoMomento();
        }
    }

    private BigDecimal calculaTotalComDesconto(CupomAplicado cupom) {
        return totalCompra.multiply(BigDecimal.valueOf(1-(cupom.getPercentualDescontoMomento()/100)))
                .setScale(2);
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getPais() {
        return pais;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public String getEstado() {
        return estado;
    }

    public PedidoResponse getPedido() {
        return pedido;
    }

    public BigDecimal getTotalCompra() {
        return totalCompra;
    }

    public boolean isExisteCupom() {
        return existeCupom;
    }

    public Double getValorCupom() {
        return valorCupom;
    }
}
