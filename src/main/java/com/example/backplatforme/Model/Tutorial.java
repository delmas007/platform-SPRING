package com.example.backplatforme.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String description;
    private Double coutMensuel;
    private Date dateDebut;

    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;


}
