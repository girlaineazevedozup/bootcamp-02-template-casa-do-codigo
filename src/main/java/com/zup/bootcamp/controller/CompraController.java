package com.zup.bootcamp.controller;

import com.zup.bootcamp.model.Compra;
import com.zup.bootcamp.request.CompraRequest;
import com.zup.bootcamp.validation.DocumentoCpfCnpjValidator;
import com.zup.bootcamp.validation.EstadoPaisValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class CompraController {

    @Autowired
    private EstadoPaisValidator estadoPaisValidator;

    @PersistenceContext
    private EntityManager manager;

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(new DocumentoCpfCnpjValidator(), estadoPaisValidator);
    }

    @PostMapping("/compra")
    public String criaCompra(@RequestBody @Valid CompraRequest compraRequest){
        Compra compra = compraRequest.toModel(manager);
        return compra.toString();
    }
}
