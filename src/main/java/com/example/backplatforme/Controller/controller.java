package com.example.backplatforme.Controller;

import com.example.backplatforme.Service.Imp.EtudiantServiceImpl;
import com.example.backplatforme.Service.Imp.TutorialServiceImp;
import com.example.backplatforme.dto.EtudiantDto;
import com.example.backplatforme.dto.TutorialDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
