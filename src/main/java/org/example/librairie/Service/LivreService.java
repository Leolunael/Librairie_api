package org.example.librairie.Service;

import lombok.RequiredArgsConstructor;
import org.example.librairie.Exception.LivreNotFoundException;
import org.example.librairie.Interfaces.ILivreService;
import org.example.librairie.Model.DTO.LivreRequestPayload;
import org.example.librairie.Model.DTO.LivreResponsePayload;
import org.example.librairie.Model.Entity.Livre;
import org.example.librairie.Repository.LivreRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LivreService implements ILivreService {

    private final LivreRepository livreRepository;

    @Override
    public Collection<LivreResponsePayload> findAll() {
        return livreRepository.findAll().stream().map(this::toDto).collect(Collectors.toSet());
    }

    @Override
    public LivreResponsePayload findById(UUID id) {
        return toDto(livreRepository.findById(id).orElseThrow(() -> new LivreNotFoundException(id)));
    }

    @Override
    public LivreResponsePayload create(LivreRequestPayload payload) {
        return toDto(livreRepository.save(toEntity(payload)));
    }

    @Override
    public boolean updateById(UUID id, LivreRequestPayload payload) {
        Livre found = livreRepository.findById(id).orElseThrow(() -> new LivreNotFoundException(id));

        found.setTitre(payload.titre());
        found.setGenre(payload.genre());
        found.setAuteur(payload.auteur());

        livreRepository.save(found);
        return true;
    }

    @Override
    public boolean deleteById(UUID id) {
        if (!livreRepository.existsById(id)) throw new LivreNotFoundException(id);
        livreRepository.deleteById(id);
        return true;
    }

    private Livre toEntity(LivreRequestPayload payload) {
        return Livre.builder()
                .titre(payload.titre())
                .genre(payload.genre())
                .auteur(payload.auteur())
                .build();
    }

    private LivreResponsePayload toDto(Livre entity) {
        return new LivreResponsePayload(entity.getId(), entity.getTitre(), entity.getGenre(), entity.getAuteur());
    }
}
