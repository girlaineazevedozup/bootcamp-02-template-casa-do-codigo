package com.zup.bootcamp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Embeddable
public class CupomAplicado {

    @ManyToOne
    private Cupom cupom;

    @Positive
    @NotNull
    @JsonProperty("percentual_desconto_momento")
    private double percentualDescontoMomento;

    @NotNull
    @Future
    @JsonProperty("validade_momento")
    private LocalDate validadeMomento;

    @Deprecated
    public CupomAplicado(){
    }

    public CupomAplicado(Cupom cupom) {
        this.cupom = cupom;
        this.percentualDescontoMomento = cupom.getPercentualDesconto();
        this.validadeMomento = cupom.getValidade();
    }

    @Override
    public String toString() {
        return "CupomAplicado{" +
                "cupom=" + cupom +
                ", percentual_desconto_momento=" + percentualDescontoMomento +
                ", validade_momento=" + validadeMomento +
                '}';
    }

    public double getPercentualDescontoMomento() {
        return percentualDescontoMomento;
    }

    public void setPercentualDescontoMomento(double percentualDescontoMomento) {
        this.percentualDescontoMomento = percentualDescontoMomento;
    }
}
