package edu.miu.ea.cs544.springboot.eaproject.persistence;

import edu.miu.ea.cs544.springboot.eaproject.entities.TechnicalInterview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicalInterviewRepository extends JpaRepository<TechnicalInterview, Integer>, JpaSpecificationExecutor<TechnicalInterview> {
}

