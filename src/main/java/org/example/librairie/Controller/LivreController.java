package org.example.librairie.Controller;

import lombok.RequiredArgsConstructor;
import org.example.librairie.Interfaces.ILivreService;
import org.example.librairie.Model.DTO.LivreRequestPayload;
import org.example.librairie.Model.DTO.LivreResponsePayload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.UUID;

@RestController
@RequestMapping("/api/livre")
@RequiredArgsConstructor
public class LivreController {

    private final ILivreService livreService;

    @Value("${SERVER_PORT}")
    private String serverPort;

    @GetMapping
    public ResponseEntity<HashSet<LivreResponsePayload>> findAll() {
        return ResponseEntity.ok(new HashSet<>(livreService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivreResponsePayload> findById(@PathVariable UUID livreid) {
        return ResponseEntity.ok(livreService.findById(livreid));
    }

    @PostMapping
    public ResponseEntity<LivreResponsePayload> create(@RequestBody LivreRequestPayload livreRequestPayload) throws URISyntaxException {
        LivreResponsePayload livreResponsePayload = livreService.create(livreRequestPayload);

        URI createdLocation = new URI(String.format("http://localhost:%s/api/livre/%s", serverPort, livreResponsePayload.id()));

        return ResponseEntity.created(createdLocation).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable UUID livreID, @RequestBody LivreRequestPayload payload){
        if (livreService.updateById(livreID, payload)) return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable UUID livreID) {
        if (livreService.deleteById(livreID)) return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().build();
    }
}
