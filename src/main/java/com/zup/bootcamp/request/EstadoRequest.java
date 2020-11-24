package com.zup.bootcamp.request;

import com.zup.bootcamp.model.Estado;
import com.zup.bootcamp.model.Pais;
import com.zup.bootcamp.validation.annotation.ExistsId;
import com.zup.bootcamp.validation.annotation.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EstadoRequest {

    @NotBlank
    @UniqueValue(domainClass = Estado.class, fieldName = "nome")
    private String nome;

    @NotNull
    @ExistsId(domainClass = Pais.class, fieldName = "id")
    private Long idPais;

    public EstadoRequest(@NotBlank String nome, @NotNull Long idPais) {
        this.nome = nome;
        this.idPais = idPais;
    }

    public Estado toModel(EntityManager manager){
        return new Estado(nome, manager.find(Pais.class, idPais));
    }
}
