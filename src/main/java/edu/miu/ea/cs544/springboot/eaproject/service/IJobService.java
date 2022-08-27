package edu.miu.ea.cs544.springboot.eaproject.service;

import edu.miu.ea.cs544.springboot.eaproject.entities.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IJobService {

    public void sendNotifyForJobCRUD(Job job);
    public Job saveJob(Job job);
    public Job findJobById(int id);
    public List<Job> findAllJobs();
    public Job findAllJobsByApplication(Application application);
    public List<Job> findAllJobsByCompany(Company company);
    public Job updateJob(int id, Job job);
    public Job updateJobSkills(int id, List<Skill> skills);
    public Job updateJobByCompany(int id, Client client);
    public Job updateJobByApplication(int id, Application application);

    public void deleteJob(int id);
    public void deleteAllJobs();
    public Job createJob(Job job);
    public Job saveJobApplication(int id, Application application);
    public Job saveSkillSet(int id, List<Skill> skillList);
    public Job saveCompanyInfo(int id, Client client);


}
