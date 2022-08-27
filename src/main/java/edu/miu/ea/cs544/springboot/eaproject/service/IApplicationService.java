package edu.miu.ea.cs544.springboot.eaproject.service;

import edu.miu.ea.cs544.springboot.eaproject.entities.Application;
import edu.miu.ea.cs544.springboot.eaproject.entities.Job;
import edu.miu.ea.cs544.springboot.eaproject.entities.Skill;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;

public interface IApplicationService {

    public Application createApplication(String date, int resumeVersion);
    public Application saveJobApplication(int id, Job job);

    public Application addApplication(Application application);
    public Application editApplication(int id, Application application);
    public void deleteApplicationById(int id);
    public Application findApplicationById(int id);
}
