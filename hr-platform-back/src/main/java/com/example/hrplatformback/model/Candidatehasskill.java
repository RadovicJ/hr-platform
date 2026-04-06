package com.example.hrplatformback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "candidatehasskills")
public class Candidatehasskill {
    @EmbeddedId
    private CandidatehasskillId id;

    @MapsId("candidateIdcandidate")
    @ManyToOne(optional = false)
    @JoinColumn(name = "Candidate_idCandidate", nullable = false)
    private Candidate candidateIdcandidate;

    @MapsId("skillIdskill")
    @ManyToOne(optional = false)
    @JoinColumn(name = "Skill_idSkill", nullable = false)
    private Skill skillIdskill;


}