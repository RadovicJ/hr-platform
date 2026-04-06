package com.example.hrplatformback.controllers;

import com.example.hrplatformback.dtos.CandidatehasskillDTO;
import com.example.hrplatformback.model.Candidate;
import com.example.hrplatformback.model.Candidatehasskill;
import com.example.hrplatformback.model.CandidatehasskillId;
import com.example.hrplatformback.model.Skill;
import com.example.hrplatformback.repositories.SkillRepository;
import com.example.hrplatformback.services.CandidateService;
import com.example.hrplatformback.services.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/skill")
public class SkillController {

    private final SkillService skillService;
    private final CandidateService candidateService;

    @PostMapping("/add")
    public ResponseEntity<Skill> addSkill(@RequestBody Skill s) {
        if (s == null || s.getSkillName().isBlank()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Skill saved = skillService.addSkill(s);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PostMapping("/update")
    public ResponseEntity<Candidatehasskill> updateCandidateSkill(@RequestBody CandidatehasskillDTO dto) {
        Optional<Skill> optS = skillService.getSkillById(dto.getSkillId());
        Optional<Candidate> optC = candidateService.getCandidateById(dto.getCandidateId());
        if (optS.isEmpty() || optC.isEmpty() || skillService.getIfCandidateHasSkill(optS.get(), optC.get()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Candidatehasskill chs = new Candidatehasskill();
        chs.setId(new CandidatehasskillId());
        chs.getId().setSkillIdskill(dto.getSkillId());
        chs.getId().setCandidateIdcandidate(dto.getCandidateId());
        chs.setSkillIdskill(optS.get());
        chs.setCandidateIdcandidate(optC.get());
        Candidatehasskill updated = skillService.addCandidateSkill(chs);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Candidatehasskill> removeCandidateSkill(@RequestBody CandidatehasskillDTO dto) {
        Optional<Skill> optS = skillService.getSkillById(dto.getSkillId());
        Optional<Candidate> optC = candidateService.getCandidateById(dto.getCandidateId());
        if (optS.isEmpty() || optC.isEmpty() || skillService.getIfCandidateHasSkill(optS.get(), optC.get()).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Candidatehasskill chs = skillService.getIfCandidateHasSkill(optS.get(), optC.get()).get();
        Candidatehasskill deleted = skillService.deleteCandidateSkill(chs);
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Skill>> getAllSkills() {
        return ResponseEntity.ok(skillService.getAllSkills());
    }
}
