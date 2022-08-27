package edu.miu.ea.cs544.springboot.eaproject.service;

import edu.miu.ea.cs544.springboot.eaproject.entities.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICompanyService {

    public Client saveClient(Client client);
    public Client editClient(int id, Client client);
    public void deleteClientById(int id);
    public Client findClientById(int id);

    public Recruiter addRecruiter(Recruiter recruiter);
    public Recruiter editRecruiter(int id, Recruiter recruiter);
    public void deleteRecruiterById(int id);
    public Recruiter findRecruiterById(int id);

    public Client newClient(String name, String mission, String reason, String website, Address address);

    public Recruiter newRecruiter(String name, Address address, List<Client> clientList);

    public Recruiter saveRecruiter(Recruiter recruiter);

    public Company saveCompanyAddress(int id, Address address);

    public Company saveJobList(int id, List<Job> jobList);

}
