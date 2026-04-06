package com.example.hrplatformback.repositories;

import com.example.hrplatformback.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
}
