package com.zup.bootcamp.response;

public class LivroResponse {

    private Long id;
    private String titulo;

    public LivroResponse(){
    }

    public LivroResponse(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
