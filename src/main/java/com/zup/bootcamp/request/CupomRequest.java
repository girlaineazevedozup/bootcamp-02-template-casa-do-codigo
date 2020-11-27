package com.zup.bootcamp.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.bootcamp.model.Cupom;
import com.zup.bootcamp.validation.annotation.UniqueValue;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CupomRequest {

    @NotBlank
    @UniqueValue(domainClass = Cupom.class, fieldName = "codigo")
    private String codigo;

    @Positive
    @NotNull
    @JsonProperty("percentual_desconto")
    private double percentualDesconto;

    @Future
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate validade;

    public CupomRequest(@NotBlank String codigo,
                        @Positive @NotNull BigDecimal percentualDesconto) {
        super();
        this.codigo = codigo;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public Cupom toModel(){
        return new Cupom(codigo, percentualDesconto, validade);
    }
}
