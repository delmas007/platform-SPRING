package com.example.backplatforme.Repository;

import com.example.backplatforme.Model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, String> {
}
