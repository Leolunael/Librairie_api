package org.example.librairie.Interfaces;

import org.example.librairie.Model.DTO.LivreRequestPayload;
import org.example.librairie.Model.DTO.LivreResponsePayload;

import java.util.Collection;
import java.util.UUID;

public interface ILivreService {
    Collection<LivreResponsePayload> findAll();
    LivreResponsePayload findById(UUID id);
    LivreResponsePayload create(LivreRequestPayload payload);
    boolean updateById(UUID uuid,  LivreRequestPayload payload) ;
    boolean deleteById(UUID id);
}
