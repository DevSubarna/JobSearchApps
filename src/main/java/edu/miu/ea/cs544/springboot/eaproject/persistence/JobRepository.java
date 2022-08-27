package edu.miu.ea.cs544.springboot.eaproject.persistence;

import edu.miu.ea.cs544.springboot.eaproject.entities.Application;
import edu.miu.ea.cs544.springboot.eaproject.entities.Company;
import edu.miu.ea.cs544.springboot.eaproject.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer>, JpaSpecificationExecutor<Job> {
    public Job findByJobApplication(Application application);
    public List<Job> findByCompanyName(Company company);

}

