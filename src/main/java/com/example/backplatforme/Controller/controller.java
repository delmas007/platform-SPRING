package com.example.backplatforme.Controller;

import com.example.backplatforme.Service.Imp.EtudiantServiceImpl;
import com.example.backplatforme.Service.Imp.TutorialServiceImp;
import com.example.backplatforme.dto.EtudiantDto;
import com.example.backplatforme.dto.TutorialDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class controller {
    EtudiantServiceImpl etudiantService;
    TutorialServiceImp tutorialService;



    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/tuto/")
    public void addTutoriats(@RequestBody TutorialDto dto) {
        tutorialService.addTutoriat(dto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/connexion/")
    public ResponseEntity<Map<String, String>> connexion(@RequestBody Map<String, String> authentification) {
        System.out.println(authentification);
        String username = authentification.get("username");
        String password = authentification.get("password");
        return etudiantService.Connexion( username, password);
    }

    @GetMapping("/tutos/")
    public List<TutorialDto> getAllTutoriat() {
        return tutorialService.getTutoriat();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/inscription/")
    public EtudiantDto inscription(@RequestBody EtudiantDto etudiantDto) {
        return etudiantService.inscription(etudiantDto);
    }


}
