package com.example.hrplatformback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCandidate", nullable = false)
    private Integer id;

    @Column(name = "fullname", nullable = false, length = 45)
    private String fullname;

    @Column(name = "birthDate", nullable = false)
    private LocalDate birthDate;

    @Column(name = "contact", nullable = false, length = 15)
    private String contact;

    @Column(name = "email", nullable = false, length = 45)
    private String email;


}