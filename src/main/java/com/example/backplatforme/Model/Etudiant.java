package com.example.backplatforme.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Etudiant {


    @Id
    private String id;

    private String username;
    private String password;
    private String prenom;
    private String nom;
    private String telephone;
    private String email;
    private String universite;
    private String societe;
    private String photo;


    @OneToMany(mappedBy = "etudiant")
    private Set<Tutorial> tutorials;



}
