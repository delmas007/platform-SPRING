package com.example.backplatforme.Repository;

import com.example.backplatforme.Model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, String> {

    Optional<Etudiant> findByUsername(String username);

    Optional<Etudiant> findEtudiantById(String id);

}
