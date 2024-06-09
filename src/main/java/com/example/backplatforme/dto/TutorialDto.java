package com.example.backplatforme.dto;

import com.example.backplatforme.Model.Etudiant;
import com.example.backplatforme.Model.Tutorial;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class TutorialDto {

    private String titre;
    private String description;
    private Double coutMensuel;
    private Date dateDebut;
    private Etudiant etudiant;
    private String etudiants;
    private LocalDateTime datePublication;

    public static TutorialDto fromEntity(Tutorial tutorial) {
        if (tutorial == null) {
            return null;
        }

        return TutorialDto.builder()
                .titre(tutorial.getTitre())
                .description(tutorial.getDescription())
                .coutMensuel(tutorial.getCoutMensuel())
                .dateDebut(tutorial.getDateDebut())
                .datePublication(tutorial.getDatePublication())
                .etudiant(tutorial.getEtudiant())
                .build();
    }

    public static Tutorial toEntity(TutorialDto tutorialDto) {
        if (tutorialDto == null) {
            return null;
        }

        return Tutorial.builder()
                .titre(tutorialDto.getTitre())
                .description(tutorialDto.getDescription())
                .coutMensuel(tutorialDto.getCoutMensuel())
                .dateDebut(tutorialDto.getDateDebut())
                .etudiant(tutorialDto.getEtudiant())
                .build();
    }
}
