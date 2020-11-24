package com.zup.bootcamp.request;

import com.zup.bootcamp.model.Pais;
import com.zup.bootcamp.validation.annotation.UniqueValue;

import javax.validation.constraints.NotBlank;

public class PaisRequest {

    @NotBlank
    @UniqueValue(domainClass = Pais.class, fieldName = "nome")
    private String nome;
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
