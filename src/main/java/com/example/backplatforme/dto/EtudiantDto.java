package com.example.backplatforme.dto;

import com.example.backplatforme.Model.Etudiant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class EtudiantDto {
    private String id;
    private String username;
    private String password;
    private String prenom;
    private String nom;
    private String telephone;
    private String email;
    private String universite;
    private String societe;

    public static EtudiantDto fromEntity(Etudiant etudiant) {
        if (etudiant == null) {
            return null;
        }

        return EtudiantDto.builder()
                .id(etudiant.getId())
                .username(etudiant.getUsername())
                .password(etudiant.getPassword())
                .prenom(etudiant.getPrenom())
                .nom(etudiant.getNom())
                .telephone(etudiant.getTelephone())
                .email(etudiant.getEmail())
                .universite(etudiant.getUniversite())
                .societe(etudiant.getSociete())
                .build();
    }

    public static Etudiant toEntity(EtudiantDto etudiantDto) {
        if (etudiantDto == null) {
            return null;
        }

        return Etudiant.builder()
                .id(etudiantDto.getId())
                .username(etudiantDto.getUsername())
                .password(etudiantDto.getPassword())
                .prenom(etudiantDto.getPrenom())
                .nom(etudiantDto.getNom())
                .telephone(etudiantDto.getTelephone())
                .email(etudiantDto.getEmail())
                .universite(etudiantDto.getUniversite())
                .societe(etudiantDto.getSociete())
                .build();
    }
}
