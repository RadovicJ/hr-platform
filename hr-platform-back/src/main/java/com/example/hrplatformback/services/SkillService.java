package com.example.hrplatformback.services;

import com.example.hrplatformback.model.Candidate;
import com.example.hrplatformback.model.Candidatehasskill;
import com.example.hrplatformback.model.Skill;
import com.example.hrplatformback.repositories.CandidateHasSkillRepository;
import com.example.hrplatformback.repositories.CandidateRepository;
import com.example.hrplatformback.repositories.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;
    private final CandidateHasSkillRepository candidateHasSkillRepository;
    private final CandidateRepository candidateRepository;

    public Skill addSkill(Skill s) {
        return skillRepository.save(s);
    }

    public Candidatehasskill addCandidateSkill(Candidatehasskill chs) {
        return candidateHasSkillRepository.save(chs);
    }

    public Candidatehasskill deleteCandidateSkill(Candidatehasskill chs) {
        candidateHasSkillRepository.delete(chs);
        return chs;
    }

    public Optional<Candidatehasskill> getIfCandidateHasSkill(Skill s, Candidate c) {
        return candidateHasSkillRepository.findBySkillIdskillAndCandidateIdcandidate(s, c);
    }

    public Optional<Skill> getSkillById(Integer id) {
        return skillRepository.findById(id);
    }

    public List<Skill> getAllSkills() {
        return  skillRepository.findAll();
    }
}
