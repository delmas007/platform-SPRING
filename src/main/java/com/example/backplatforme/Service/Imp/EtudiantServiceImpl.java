package com.example.backplatforme.Service.Imp;

import com.example.backplatforme.Exception.EntityNotFoundException;
import com.example.backplatforme.Exception.ErrorCodes;
import com.example.backplatforme.Model.Etudiant;
import com.example.backplatforme.Repository.EtudiantRepository;
import com.example.backplatforme.Service.EtudiantService;
import com.example.backplatforme.dto.EtudiantDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class EtudiantServiceImpl implements EtudiantService {

    EtudiantRepository etudiantRepository;
    @Override
    public EtudiantDto inscription(EtudiantDto etudiantDto) {
        Etudiant existingUser = etudiantRepository.findByUsername(etudiantDto.getUsername()).orElse(null);
        if (existingUser == null) {
            Etudiant newUser = Etudiant.builder()
                    .id(UUID.randomUUID().toString())
                    .username(etudiantDto.getUsername())
                    .prenom(etudiantDto.getPrenom())
                    .nom(etudiantDto.getNom())
                    .telephone(etudiantDto.getTelephone())
                    .email(etudiantDto.getEmail())
                    .universite(etudiantDto.getUniversite())
                    .societe(etudiantDto.getSociete())
                    .build();
            Etudiant savedUser = etudiantRepository.save(newUser);
            return EtudiantDto.fromEntity(savedUser);
        } else {
            throw new EntityNotFoundException("Etudiant existe deja", ErrorCodes.UTILISATEUR_DEJA_EXIST);
        }
    }

    @Override
    public EtudiantDto loadUserByUsername(String username) {
        Optional<Etudiant> user = etudiantRepository.findByUsername(username);
        return EtudiantDto.fromEntity(user.orElseThrow(()-> new EntityNotFoundException("Utilisateur pas trouver ",
                ErrorCodes.UTILISATEUR_PAS_TROUVER)));

    }
}
