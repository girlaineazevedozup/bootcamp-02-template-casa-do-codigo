package com.zup.bootcamp.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.bootcamp.model.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DetalheLivroResponse {

    private DetalheAutorResponse autor;

    private String titulo;

    private String isbn;

    @JsonProperty("numero_paginas")
    private int numeroPaginas;

    private BigDecimal preco;

    private String resumo;

    private String sumario;

    @JsonProperty("data_publicacao")
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataPublicacao;

    public DetalheLivroResponse(Livro livro) {
        titulo = livro.getTitulo();
        autor = new DetalheAutorResponse(livro.getAutor());
        isbn = livro.getIsbn();
        numeroPaginas = livro.getNumeroPaginas();
        preco = livro.getPreco();
        resumo = livro.getResumo();
        sumario = livro.getSumario();
        dataPublicacao = livro.getDataPublicacao();
    }

    public DetalheAutorResponse getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }
}
