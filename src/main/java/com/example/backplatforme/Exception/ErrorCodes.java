package com.example.backplatforme.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodes {
    UTILISATEUR_PAS_TROUVER(900),
    UTILISATEUR_DEJA_EXIST(800),
    CODE_INVALIDE(700),
    CODE_EXPIRE(600);



    private final int code;
}
