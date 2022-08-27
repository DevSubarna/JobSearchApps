package edu.miu.ea.cs544.springboot.eaproject.controller;

import edu.miu.ea.cs544.springboot.eaproject.entities.Application;
import edu.miu.ea.cs544.springboot.eaproject.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portal")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/application/{id}")
    public Application findApplicationById(@PathVariable int id) {
        return applicationService.findApplicationById(id);
    }

    @PostMapping("/application/new")
    public Application addNewApplication(@RequestBody Application application) {
        return applicationService.addApplication(application);
    }

    @PutMapping("/application/edit/{id}")
    public Application editApplication(@PathVariable int id, @RequestBody Application application) {
        return applicationService.editApplication(id, application);
    }

    @DeleteMapping("/application/delete/{id}")
    public void deleteApplicationById(@PathVariable int id) {
        applicationService.deleteApplicationById(id);
    }
}
