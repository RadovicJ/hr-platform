package com.example.hrplatformback.services;

import com.example.hrplatformback.model.Candidate;
import com.example.hrplatformback.model.Skill;
import com.example.hrplatformback.repositories.CandidateHasSkillRepository;
import com.example.hrplatformback.repositories.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final CandidateHasSkillRepository candidateHasSkillRepository;

    public Candidate addCandidate(Candidate c) {
        return candidateRepository.save(c);
    }

    public Candidate removeCandidate(Candidate c) {
        candidateHasSkillRepository.removeCandidatehasskillByCandidateIdcandidate(c);
        candidateRepository.delete(c);
        return c;
    }

    public Optional<Candidate> getCandidateByFullname(String fullname) {
        return candidateRepository.findByFullname(fullname);
    }

    public List<Candidate> getAllCandidatesWithSkills(List<Integer> skillIds) {
        List<Integer> candidateIds = candidateHasSkillRepository.findDistinctCandidatesBySkills(skillIds);
        List<Candidate> candidates = candidateIds.stream()
                .map(this::getCandidateById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        return candidates;
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public Optional<Candidate> getCandidateById(Integer id) {
        return candidateRepository.findById(id);
    }
}
