package com.example.hrplatformback.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class CandidatehasskillId implements Serializable {
    private static final long serialVersionUID = 2899208950507352978L;
    @Column(name = "idCandidateHasSkills", nullable = false)
    private Integer idCandidateHasSkills;

    @Column(name = "Candidate_idCandidate", nullable = false)
    private Integer candidateIdcandidate;

    @Column(name = "Skill_idSkill", nullable = false)
    private Integer skillIdskill;


}