package edu.miu.ea.cs544.springboot.eaproject.service;

import edu.miu.ea.cs544.springboot.eaproject.entities.*;
import edu.miu.ea.cs544.springboot.eaproject.exception.NotFoundException;
import edu.miu.ea.cs544.springboot.eaproject.messaging.sender.Sender;
import edu.miu.ea.cs544.springboot.eaproject.persistence.ClientRepository;
import edu.miu.ea.cs544.springboot.eaproject.persistence.JobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class JobService implements IJobService, ErrorHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private Sender sender;

    @Override
    public void sendNotifyForJobCRUD(Job job) {
        sender.send1(job.toString());
    }

    @Override
    public Job findJobById(int id) {
        return findJob(id);
    }

    @Override
    public List<Job> findAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job findAllJobsByApplication(Application application) {
        return jobRepository.findByJobApplication(application);
    }

    @Override
    public List<Job> findAllJobsByCompany(Company company) {
        return jobRepository.findByCompanyName(company);
    }

    @Override
    public Job updateJob(int id, Job job) {
        Optional<Job> editJob = jobRepository.findById(id);
        if(editJob.isPresent()) {
            Job tempJob = editJob.get();
            tempJob.setTitle(job.getTitle());
            tempJob.setSalary(job.getSalary());
            tempJob.setCompanyName(job.getCompanyName());
            tempJob.setJobApplication(job.getJobApplication());
            tempJob.setSkillList(job.getSkillList());
            sendNotifyForJobCRUD(tempJob);
            return jobRepository.save(tempJob);
        } else {
            throw new NotFoundException("Job " + id + " not found");
        }
    }

    @Override
    public Job updateJobSkills(int id, List<Skill> skills) {
        Optional<Job> editJob = jobRepository.findById(id);
        if(editJob.isPresent()) {
            Job tempJob = editJob.get();
            tempJob.setSkillList(skills);
            sendNotifyForJobCRUD(tempJob);
            return jobRepository.save(tempJob);
        } else {
            throw new NotFoundException("Job " + id + " not found");
        }
    }

    @Override
    public Job updateJobByCompany(int id, Client client) {
        Optional<Job> editJob = jobRepository.findById(id);
        if(editJob.isPresent()) {
            Job tempJob = editJob.get();
            tempJob.setCompanyName(client);
            sendNotifyForJobCRUD(tempJob);
            return jobRepository.save(tempJob);
        } else {
            throw new NotFoundException("Job " + id + " not found");
        }
    }

    @Override
    public Job updateJobByApplication(int id, Application application) {
        Optional<Job> editJob = jobRepository.findById(id);
        if(editJob.isPresent()) {
            Job tempJob = editJob.get();
            tempJob.setJobApplication(application);
            sendNotifyForJobCRUD(tempJob);
            return jobRepository.save(tempJob);
        } else {
            throw new NotFoundException("Job " + id + " not found");
        }
    }

    @Override
    public void deleteJob(int id) {
        jobRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Job " + id + " not found"));
        Optional<Job> tempJob = jobRepository.findById(id);
        if(tempJob.isPresent()) sendNotifyForJobCRUD(tempJob.get());
        jobRepository.deleteById(id);
    }

    @Override
    public void deleteAllJobs() {
        sender.send1("All Jobs deleted");
        jobRepository.deleteAll();
    }

    private Job findJob(int id) {
        Optional<Job> job = jobRepository.findById(id);
        if(job.isPresent()) {
            return job.get();
        } else throw new NotFoundException("Job Not Found");
    }

    @Override
    public Job createJob(Job job) {
        sendNotifyForJobCRUD(job);
        return jobRepository.save(job);
    }

    @Override
    public Job saveJobApplication(int id, Application application) {
       Job searchJob = findJob(id);
       searchJob.setJobApplication(application);
       return jobRepository.save(searchJob);
    }

    @Override
    public Job saveSkillSet(int id, List<Skill> skillList) {
        Job searchJob = findJob(id);
        searchJob.setSkillList(skillList);
        return jobRepository.save(searchJob);
    }

    @Override
    public Job saveCompanyInfo(int id, Client client) {
        Job searchJob = findJob(id);
        searchJob.setCompanyName(client);
        return jobRepository.save(searchJob);
    }
    @Override
    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public void handleError(Throwable t) {
        log.error("Error in listener", t);
    }
}
