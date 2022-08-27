package edu.miu.ea.cs544.springboot.eaproject.persistence;

import edu.miu.ea.cs544.springboot.eaproject.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRespository extends JpaRepository<Company, Integer>, JpaSpecificationExecutor<Company> {
}
