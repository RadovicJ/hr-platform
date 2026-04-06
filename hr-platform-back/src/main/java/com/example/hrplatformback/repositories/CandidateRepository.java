package com.example.hrplatformback.repositories;

import com.example.hrplatformback.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    Optional<Candidate> findByFullname(String fullname);
}
