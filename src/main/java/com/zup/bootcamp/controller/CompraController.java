package com.zup.bootcamp.controller;

import com.zup.bootcamp.infrastructure.CupomRepository;
import com.zup.bootcamp.model.Compra;
import com.zup.bootcamp.request.CompraRequest;
import com.zup.bootcamp.response.DetalheCompraResponse;
import com.zup.bootcamp.response.DetalheLivroResponse;
import com.zup.bootcamp.validation.DocumentoCpfCnpjValidator;
import com.zup.bootcamp.validation.EstadoPaisValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private EstadoPaisValidator estadoPaisValidator;

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private CupomRepository cupomRepository;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(new DocumentoCpfCnpjValidator(), estadoPaisValidator);
    }

    @PostMapping
    @Transactional
    public String criaCompra(@RequestBody @Valid CompraRequest compraRequest) {
        Compra compra = compraRequest.toModel(manager, cupomRepository);
        manager.persist(compra);
        return compra.toString();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<?> detalhaCompra(@PathVariable("id") Long id) {

        Compra compra = manager.find(Compra.class, id);

        if(compra == null)
            return ResponseEntity.notFound().build();

        DetalheCompraResponse detalheLivroResponse = new DetalheCompraResponse(compra);
        return ResponseEntity.ok(detalheLivroResponse);
    }
}
