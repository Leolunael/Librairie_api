package org.example.librairie.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Attention vous abimez le livre")
public class LivreNotFoundException extends RuntimeException {
    public LivreNotFoundException(UUID id) {

        super(String.format("Livre with id is not found", id));
    }
}
