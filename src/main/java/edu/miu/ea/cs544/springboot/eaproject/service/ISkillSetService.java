package edu.miu.ea.cs544.springboot.eaproject.service;

import edu.miu.ea.cs544.springboot.eaproject.entities.Job;
import edu.miu.ea.cs544.springboot.eaproject.entities.Skill;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ISkillSetService {
    public Skill saveSkill(String name, String experience, String description, String language);
    public Skill saveJobRef(int id, Job job);

    public Skill addSkill(Skill skill);
    public Skill editSkill(int id, Skill skill);
    public void deleteSkillById(int id);
    public Skill findSkillById(int id);
}
