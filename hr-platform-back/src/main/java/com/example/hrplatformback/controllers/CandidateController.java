package com.example.hrplatformback.controllers;

import com.example.hrplatformback.model.Candidate;
import com.example.hrplatformback.model.Skill;
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
@RequestMapping("/candidate")
public class CandidateController {

    private final CandidateService candidateService;
    private final SkillService skillService;

    @PostMapping("/add")
    public ResponseEntity<Candidate> addCandidate(@RequestBody Candidate c) {
        if (c == null || c.getFullname().isBlank() || c.getBirthDate() == null || c.getContact().isBlank() || c.getEmail().isBlank()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Candidate saved = candidateService.addCandidate(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Candidate> removeCandidate(@RequestParam("id") Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Candidate> opt = candidateService.getCandidateById(id);
        if (opt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Candidate removed = candidateService.removeCandidate(opt.get());
        return ResponseEntity.ok(removed);
    }

    @GetMapping("/getByFullname")
    public ResponseEntity<Candidate> getCandidateByFullname(@RequestParam("fullname") String fullname) {
        if (fullname == null || fullname.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Optional<Candidate> opt = candidateService.getCandidateByFullname(fullname);
        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/getWithSkills")
    public ResponseEntity<List<Candidate>> getCandidatesWithSkills(@RequestBody List<Integer> skillIds) {
        for (Integer skillId : skillIds) {
            if (skillService.getSkillById(skillId).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        List<Candidate> l = candidateService.getAllCandidatesWithSkills(skillIds);
        return ResponseEntity.ok(l);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        return ResponseEntity.ok(candidateService.getAllCandidates());
    }

    @GetMapping("/getById")
    public ResponseEntity<Candidate> getById(@RequestParam Integer id) {
        Optional<Candidate> opt = candidateService.getCandidateById(id);
        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
