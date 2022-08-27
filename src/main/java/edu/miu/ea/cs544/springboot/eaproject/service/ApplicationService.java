package edu.miu.ea.cs544.springboot.eaproject.service;

import edu.miu.ea.cs544.springboot.eaproject.entities.*;
import edu.miu.ea.cs544.springboot.eaproject.entities.Application;
import edu.miu.ea.cs544.springboot.eaproject.exception.NotFoundException;
import edu.miu.ea.cs544.springboot.eaproject.persistence.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.Date;
import java.util.Optional;

@Service
public class ApplicationService implements IApplicationService{

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public Application createApplication(String date, int resumeVersion) {
        Application application = new Application(date,resumeVersion);
        return applicationRepository.save(application);
    }

    private Application findApplication(int id) {
        Optional<Application> application = applicationRepository.findById(id);
        if(application.isPresent()) {
            return application.get();
        } else throw new NotFoundException("Application Not Found");
    }

    @Override
    public Application saveJobApplication(int id, Job job) {
        Application searchApp = findApplication(id);
        searchApp.setApplicationJob(job);
        return applicationRepository.save(searchApp);
    }

    @Override
    public Application addApplication(Application application) {
        return applicationRepository.save(application);
    }

    @Override
    public Application editApplication(int id, Application application) {
        Optional<Application> editApplication = applicationRepository.findById(id);
        if(editApplication.isPresent()) {
            Application tempApplication = editApplication.get();
            tempApplication.setDate(application.getDate());
            tempApplication.setResumeVersion(application.getResumeVersion());
            tempApplication.setApplicationJob(application.getApplicationJob());
            return applicationRepository.save(tempApplication);
        } else {
            throw new NotFoundException("Application " + id + " not found");
        }
    }

    @Override
    public void deleteApplicationById(int id) {
        applicationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Application " + id + " not found"));
        applicationRepository.deleteById(id);
    }

    @Override
    public Application findApplicationById(int id) {
        Optional<Application> application = applicationRepository.findById(id);
        if(application.isPresent()) {
            return application.get();
        } else {
            throw new NotFoundException("Application " + id + " not found");
        }
    }
}
