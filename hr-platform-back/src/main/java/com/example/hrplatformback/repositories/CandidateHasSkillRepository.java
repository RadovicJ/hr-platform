package com.example.hrplatformback.repositories;

import com.example.hrplatformback.model.Candidate;
import com.example.hrplatformback.model.Candidatehasskill;
import com.example.hrplatformback.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CandidateHasSkillRepository extends JpaRepository<Candidatehasskill, Integer> {
    @Query("SELECT DISTINCT chs.candidateIdcandidate.id FROM Candidatehasskill chs WHERE chs.skillIdskill.id IN :skillIds")
    List<Integer> findDistinctCandidatesBySkills(@Param("skillIds") List<Integer> skillIds);

    void removeCandidatehasskillByCandidateIdcandidate(Candidate candidateIdcandidate);

    Optional<Candidatehasskill> findBySkillIdskillAndCandidateIdcandidate(Skill skillIdskill, Candidate candidateIdcandidate);
}
