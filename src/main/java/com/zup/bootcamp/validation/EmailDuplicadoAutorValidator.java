package com.zup.bootcamp.validation;

import com.zup.bootcamp.infrastructure.AutorRepository;
import com.zup.bootcamp.model.Autor;
import com.zup.bootcamp.request.AutorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class EmailDuplicadoAutorValidator implements Validator {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return AutorRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors())
            return;

        AutorRequest request = (AutorRequest) target;
        Optional<Autor> optionalAutor = autorRepository.findByEmail(request.getEmail());

        if(optionalAutor.isPresent())
            errors.rejectValue("email", null, "Já existe um autor com este email: " +
                    request.getEmail());
    }
}
