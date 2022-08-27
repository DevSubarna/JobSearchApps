package edu.miu.ea.cs544.springboot.eaproject.service;

import edu.miu.ea.cs544.springboot.eaproject.entities.Skill;
import edu.miu.ea.cs544.springboot.eaproject.entities.Job;
import edu.miu.ea.cs544.springboot.eaproject.exception.NotFoundException;
import edu.miu.ea.cs544.springboot.eaproject.persistence.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class SkillSetService implements ISkillSetService{

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public Skill saveSkill(String name, String experience, String description, String language) {
        return null;
    }

    private Skill findSkill(int id) {
        Optional<Skill> skill = skillRepository.findById(id);
        if(skill.isPresent()) {
            return skill.get();
        } else throw new NotFoundException("Skill Not Found");
    }

    @Override
    public Skill saveJobRef(int id, Job job) {
        Skill searchSkill = findSkill(id);
        searchSkill.setRefJob(job);
        return skillRepository.save(searchSkill);
    }

    @Override
    public Skill addSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public Skill editSkill(int id, Skill skill) {
        Optional<Skill> editSkill = skillRepository.findById(id);
        if(editSkill.isPresent()) {
            Skill tempSkill = editSkill.get();
            tempSkill.setName(skill.getName());
            tempSkill.setDescription(skill.getDescription());
            tempSkill.setExperience(skill.getExperience());
            tempSkill.setLanguage(skill.getLanguage());
            return skillRepository.save(tempSkill);
        } else {
            throw new NotFoundException("Skill " + id + " not found");
        }
    }

    @Override
    public void deleteSkillById(int id) {
        skillRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Skill " + id + " not found"));
        skillRepository.deleteById(id);
    }

    @Override
    public Skill findSkillById(int id) {
        Optional<Skill> skill = skillRepository.findById(id);
        if(skill.isPresent()) {
            return skill.get();
        } else {
            throw new NotFoundException("Skill " + id + " not found");
        }
    }
}
