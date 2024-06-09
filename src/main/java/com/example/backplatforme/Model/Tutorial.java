package com.example.backplatforme.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tutorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    @Lob // Pour les champs texte longs
    private String description;
    private Double coutMensuel;
    private Date dateDebut;
    private LocalDateTime datePublication;

    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;


    @PrePersist
    protected void onCreate() {
        datePublication = LocalDateTime.now();
    }
}
