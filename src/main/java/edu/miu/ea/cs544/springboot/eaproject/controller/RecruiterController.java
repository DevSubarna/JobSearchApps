package edu.miu.ea.cs544.springboot.eaproject.controller;

import edu.miu.ea.cs544.springboot.eaproject.entities.Recruiter;
import edu.miu.ea.cs544.springboot.eaproject.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portal")
public class RecruiterController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/recruiter/{id}")
    public Recruiter findRecruiterById(@PathVariable int id) {
        return companyService.findRecruiterById(id);
    }

    @PostMapping("/recruiter/new")
    public Recruiter addNewRecruiter(@RequestBody Recruiter recruiter) {
        return companyService.addRecruiter(recruiter);
    }

    @PutMapping("/recruiter/edit/{id}")
    public Recruiter editRecruiter(@PathVariable int id, @RequestBody Recruiter recruiter) {
        return companyService.editRecruiter(id, recruiter);
    }

    @DeleteMapping("/recruiter/delete/{id}")
    public void deleteRecruiterById(@PathVariable int id) {
        companyService.deleteRecruiterById(id);
    }
}
