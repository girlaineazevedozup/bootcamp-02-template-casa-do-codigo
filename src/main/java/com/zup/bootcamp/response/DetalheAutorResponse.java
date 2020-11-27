package com.zup.bootcamp.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.bootcamp.model.Autor;

public class DetalheAutorResponse {

    private String nome;
    private String descricao;

    public DetalheAutorResponse(Autor autor){
        nome = autor.getNome();
        descricao = autor.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
