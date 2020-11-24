package com.zup.bootcamp.validation;

import com.zup.bootcamp.request.CompraRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class DocumentoCpfCnpjValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return CompraRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors())
            return;

        CompraRequest request = (CompraRequest)target;

        if(!request.documentoValido()){
            errors.rejectValue("documento", null, "Documento precisa ser CPF ou CNPJ");
        }
    }
}
