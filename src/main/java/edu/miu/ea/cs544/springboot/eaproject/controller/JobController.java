package edu.miu.ea.cs544.springboot.eaproject.controller;

import edu.miu.ea.cs544.springboot.eaproject.entities.*;
import edu.miu.ea.cs544.springboot.eaproject.messaging.sender.Sender;
import edu.miu.ea.cs544.springboot.eaproject.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portal")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/job/{id}")
    public Job findjobById(@PathVariable int id) {
        return jobService.findJobById(id);
    }

    @GetMapping("/job")
    public List<Job> findAllJobs() {
        return jobService.findAllJobs();
    }


    @PostMapping("/job/create")
    public Job createNewJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }

    @PutMapping("/job/edit/{id}")
    public Job updateJob(@PathVariable int id, @RequestBody Job job) {
        return jobService.updateJob(id,job);
    }

    @PutMapping("/job/edit/{id}/skill")
    public Job editJobSkills(@PathVariable int id, @RequestBody List<Skill> skills) {
        return jobService.updateJobSkills(id,skills);
    }

    @PutMapping("/job/edit/{id}/company")
    public Job editJobCompany(@PathVariable int id, @RequestBody Client client) {
        return jobService.updateJobByCompany(id,client);
    }

    @PutMapping("/job/edit/{id}/application")
    public Job editJobApplication(@PathVariable int id, @RequestBody Application application) {
        return jobService.updateJobByApplication(id,application);
    }

    @DeleteMapping("/job/delete/{id}")
    public void deleteJob(@PathVariable int id) {
        jobService.deleteJob(id);
    }

    @DeleteMapping("/job/delete")
    public void deleteAllJobs() {
        jobService.deleteAllJobs();
    }
}
