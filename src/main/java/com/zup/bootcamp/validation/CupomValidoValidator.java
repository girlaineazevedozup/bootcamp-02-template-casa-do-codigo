package com.zup.bootcamp.validation;

import com.zup.bootcamp.infrastructure.CupomRepository;
import com.zup.bootcamp.model.Cupom;
import com.zup.bootcamp.request.CompraRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class CupomValidoValidator implements Validator {

    @Autowired
    private CupomRepository cupomRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return CompraRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors())
            return;

        CompraRequest compraRequest = (CompraRequest)target;
        Optional<String> possivelCodigo = compraRequest.getCodigoCupom();
        if(possivelCodigo.isPresent()){
            Cupom cupom = cupomRepository.findByCodigo(possivelCodigo.get());
            if(cupom.isValido()){
                errors.rejectValue("codigoCupom", null, "Este cupom não é mais válido");
            }
        }
    }
}
