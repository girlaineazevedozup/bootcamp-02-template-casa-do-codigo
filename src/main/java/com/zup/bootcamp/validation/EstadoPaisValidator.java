package com.zup.bootcamp.validation;

import com.zup.bootcamp.model.Estado;
import com.zup.bootcamp.model.Pais;
import com.zup.bootcamp.request.CompraRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.parser.Entity;

@Component
public class EstadoPaisValidator implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> aClass) {
        return CompraRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors())
            return;

        CompraRequest request = (CompraRequest) target;

        if(request.temEstado()){
            Pais pais = manager.find(Pais.class, request.getIdPais());
            Estado estado = manager.find(Estado.class, request.getIdEstado());

            if(!estado.pertencePais(pais))
                errors.reject("idEstado", null, "O estado não pertence ao país indicado");
        }
    }
}
