package org.example.librairie.Model.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "livre")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    @Column(name = "livre_id")
    private UUID id;

    @Column(name= "livre_name")
    private String titre;

    @Column(name = "livre_genre")
    private String genre;

    @Column(name = "livre_auteur")
    private String auteur;


}
