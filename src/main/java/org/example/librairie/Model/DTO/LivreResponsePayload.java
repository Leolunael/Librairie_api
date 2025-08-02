package org.example.librairie.Model.DTO;

import java.util.UUID;

public record LivreResponsePayload(UUID id,  String name, String genre, String auteur) {
}
