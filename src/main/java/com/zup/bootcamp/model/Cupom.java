package com.zup.bootcamp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String codigo;

    @Positive
    @NotNull
    @JsonProperty("percentual_desconto")
    private double percentualDesconto;

    @Future
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate validade;

    @Deprecated
    public Cupom(){
    }

    public Cupom(@NotBlank String codigo,
                 @Positive @NotNull double percentualDesconto,
                 @Future @NotNull LocalDate validade) {
        this.codigo = codigo;
        this.percentualDesconto = percentualDesconto;
        this.validade = validade;
    }

    @Override
    public String toString() {
        return "Cupom{" +
                "codigo='" + codigo + '\'' +
                ", percentual_desconto=" + percentualDesconto +
                ", validade=" + validade +
                '}';
    }

    public boolean isValido() {
        return !validade.isBefore(LocalDate.now());
    }

    public double getPercentualDesconto() {
        return percentualDesconto;
    }

    public LocalDate getValidade(){
        return validade;
    }
}
