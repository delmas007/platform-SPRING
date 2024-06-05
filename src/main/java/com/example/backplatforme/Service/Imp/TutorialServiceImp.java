package com.example.backplatforme.Service.Imp;

import com.example.backplatforme.Exception.EntityNotFoundException;
import com.example.backplatforme.Exception.ErrorCodes;
import com.example.backplatforme.Model.Tutorial;
import com.example.backplatforme.Repository.TutorialRepository;
import com.example.backplatforme.Service.TutorialService;
import com.example.backplatforme.dto.TutorialDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TutorialServiceImp implements TutorialService {

    TutorialRepository tutorialRepository;
    @Override
    public void addTutoriat(TutorialDto tutorialDto) {
        if (tutorialDto == null) {
            throw new EntityNotFoundException("Information non valide", ErrorCodes.INFORMATION_NON_VALIDE);
        }
        tutorialRepository.save(TutorialDto.toEntity(tutorialDto));
    }

    @Override
    public List<TutorialDto> getTutoriat() {
        List<Tutorial> tutorials = tutorialRepository.findAll();
        List<TutorialDto> tutorialDtos = tutorials.stream()
                .map(TutorialDto::fromEntity)
                .collect(Collectors.toList());
        return tutorialDtos;
    }

}
