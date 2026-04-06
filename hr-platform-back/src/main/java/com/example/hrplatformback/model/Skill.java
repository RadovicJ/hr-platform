package com.example.hrplatformback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSkill", nullable = false)
    private Integer id;

    @Column(name = "skillName", nullable = false, length = 45)
    private String skillName;


}