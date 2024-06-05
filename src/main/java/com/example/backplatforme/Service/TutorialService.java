package com.example.backplatforme.Service;

import com.example.backplatforme.dto.TutorialDto;

import java.util.List;

public interface TutorialService {
   public void addTutoriat(TutorialDto tutorialDto);

   public List<TutorialDto> getTutoriat();
}
