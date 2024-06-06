package com.example.backplatforme.Service.Imp;

import com.example.backplatforme.Exception.EntityNotFoundException;
import com.example.backplatforme.Exception.ErrorCodes;
import com.example.backplatforme.Model.Etudiant;
import com.example.backplatforme.Repository.EtudiantRepository;
import com.example.backplatforme.Service.EtudiantService;
import com.example.backplatforme.dto.EtudiantDto;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class EtudiantServiceImpl implements EtudiantService {

    EtudiantRepository etudiantRepository;

    public EtudiantServiceImpl(EtudiantRepository etudiantRepository, JwtEncoder jwtEncoder, @Lazy AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.etudiantRepository = etudiantRepository;
        this.jwtEncoder = jwtEncoder;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    JwtEncoder jwtEncoder;
    AuthenticationManager authenticationManager;
    PasswordEncoder passwordEncoder;
    @Override
    public EtudiantDto inscription(EtudiantDto etudiantDto) {
        Etudiant existingUser = etudiantRepository.findByUsername(etudiantDto.getUsername()).orElse(null);
        if (existingUser == null) {
            Etudiant newUser = Etudiant.builder()
                    .id(UUID.randomUUID().toString())
                    .username(etudiantDto.getUsername())
                    .password(passwordEncoder.encode(etudiantDto.getPassword()))
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

    public ResponseEntity<Map<String, String>> Connexion(String username, String password) {
        System.out.println("username: "+username);
        System.out.println("password: "+password);
        String subject=null;
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        subject=loadUserByUsername(username).getUsername();
        String nom = loadUserByUsername(username).getNom();
        String id = loadUserByUsername(username).getId();
        String prenom = loadUserByUsername(username).getPrenom();
        System.out.println("subject: "+subject);



        Map<String, String> idToken=new HashMap<>();
        Instant instant=Instant.now();
        JwtClaimsSet jwtClaimsSet=JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plus((Duration.ofMinutes(10))))
                .issuer("security-service")
                .claim("nom",nom)
                .claim("id",id)
                .claim("prenom",prenom)
                .build();
        String jwtAccessToken=jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        idToken.put("accessToken",jwtAccessToken);

        return new ResponseEntity<>(idToken, HttpStatus.OK);
    }
}
