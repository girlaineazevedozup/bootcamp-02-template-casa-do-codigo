package com.zup.bootcamp.controller;

import com.zup.bootcamp.model.Autor;
import com.zup.bootcamp.request.AutorRequest;
import com.zup.bootcamp.validation.EmailDuplicadoAutorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class AutorController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private EmailDuplicadoAutorValidator emailDuplicadoAutorValidator;

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(emailDuplicadoAutorValidator);
    }

    @PostMapping("/autor")
    @Transactional
    public String criaAutor(@Valid @RequestBody AutorRequest autorRequest) {
        Autor novoAutor = autorRequest.toModel();
        manager.persist(novoAutor);
        return novoAutor.toString();
    }
}
