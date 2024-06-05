package com.example.backplatforme.Service;

import com.example.backplatforme.dto.EtudiantDto;

public interface EtudiantService {
    EtudiantDto inscription(EtudiantDto etudiantDto);

    EtudiantDto loadUserByUsername(String username);

}
