package edu.miu.ea.cs544.springboot.eaproject.controller;

import edu.miu.ea.cs544.springboot.eaproject.entities.Address;
import edu.miu.ea.cs544.springboot.eaproject.entities.Skill;
import edu.miu.ea.cs544.springboot.eaproject.service.AddressService;
import edu.miu.ea.cs544.springboot.eaproject.service.SkillSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portal")
public class SkillController {

    @Autowired
    private SkillSetService skillSetService;

    @GetMapping("/skill/{id}")
    public Skill findSkillById(@PathVariable int id) {
        return skillSetService.findSkillById(id);
    }

    @PostMapping("/skill/new")
    public Skill addNewSkill(@RequestBody Skill skill) {
        return skillSetService.addSkill(skill);
    }

    @PutMapping("/skill/edit/{id}")
    public Skill editSkill(@PathVariable int id, @RequestBody Skill skill) {
        return skillSetService.editSkill(id, skill);
    }

    @DeleteMapping("/skill/delete/{id}")
    public void deleteSkillById(@PathVariable int id) {
        skillSetService.deleteSkillById(id);
    }
}
