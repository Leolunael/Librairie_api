package org.example.librairie.Repository;

import org.example.librairie.Model.Entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LivreRepository extends JpaRepository <Livre, UUID> {

}
