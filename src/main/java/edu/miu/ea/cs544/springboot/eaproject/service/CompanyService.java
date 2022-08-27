package edu.miu.ea.cs544.springboot.eaproject.service;

import edu.miu.ea.cs544.springboot.eaproject.entities.*;
import edu.miu.ea.cs544.springboot.eaproject.exception.NotFoundException;
import edu.miu.ea.cs544.springboot.eaproject.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService implements ICompanyService{

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RecruiterRepository recruiterRepository;

    @Autowired
    private CompanyRespository companyRespository;

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }
    
    @Override
    public Client editClient(int id, Client client) {
        Optional<Client> editClient = clientRepository.findById(id);
        if(editClient.isPresent()) {
            Client tempClient = editClient.get();
            tempClient.setName(client.getName());
            tempClient.setCompanyAddress(client.getCompanyAddress());
            tempClient.setMission(client.getMission());
            tempClient.setReason(client.getReason());
            tempClient.setWebsite(client.getWebsite());
            tempClient.setCompanyJobList(client.getCompanyJobList());
            return clientRepository.save(tempClient);
        } else {
            throw new NotFoundException("Client " + id + " not found");
        }
    }

    @Override
    public void deleteClientById(int id) {
        clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client " + id + " not found"));
        clientRepository.deleteById(id);
    }

    @Override
    public Client findClientById(int id) {
        Optional<Client> client = clientRepository.findById(id);
        if(client.isPresent()) {
            return client.get();
        } else {
            throw new NotFoundException("Client " + id + " not found");
        }
    }

    @Override
    public Recruiter addRecruiter(Recruiter recruiter) {
        return recruiterRepository.save(recruiter);
    }

    @Override
    public Recruiter editRecruiter(int id, Recruiter recruiter) {
        Optional<Recruiter> editRecruiter = recruiterRepository.findById(id);
        if(editRecruiter.isPresent()) {
            Recruiter tempRecruiter = editRecruiter.get();
            tempRecruiter.setName(recruiter.getName());
            tempRecruiter.setCompanyAddress(recruiter.getCompanyAddress());
            tempRecruiter.setCompanyJobList(recruiter.getCompanyJobList());
            tempRecruiter.setClientList(recruiter.getClientList());
            return recruiterRepository.save(tempRecruiter);
        } else {
            throw new NotFoundException("Recruiter " + id + " not found");
        }
    }

    @Override
    public void deleteRecruiterById(int id) {
        recruiterRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Recruiter " + id + " not found"));
        recruiterRepository.deleteById(id);    }

    @Override
    public Recruiter findRecruiterById(int id) {
        Optional<Recruiter> recruiter = recruiterRepository.findById(id);
        if(recruiter.isPresent()) {
            return recruiter.get();
        } else {
            throw new NotFoundException("Recruiter " + id + " not found");
        }
    }

    @Override
    public Recruiter newRecruiter(String name, Address address, List<Client> clientList) {
        Recruiter recruiter = new Recruiter(clientList);
        recruiter.setName(name);
        recruiter.setCompanyAddress(address);
        return recruiterRepository.save(recruiter);
    }


    @Override
    public Client newClient(String name, String mission, String reason, String website, Address address) {
        Client client = new Client(mission,reason,website);
        client.setName(name);
        client.setCompanyAddress(address);
        return clientRepository.save(client);
    }

    @Override
    public Recruiter saveRecruiter(Recruiter recruiter) {
        return recruiterRepository.save(recruiter);
    }

    private Company findCompany(int id) {
        Optional<Company> company = companyRespository.findById(id);
        if(company.isPresent()) {
            return company.get();
        } else throw new NotFoundException("Company Not Found");
    }

    @Override
    public Company saveCompanyAddress(int id, Address address) {
        Company searchCompany = findCompany(id);
        searchCompany.setCompanyAddress(address);
        return companyRespository.save(searchCompany);
    }

    @Override
    public Company saveJobList(int id, List<Job> jobList) {
        Company searchCompany = findCompany(id);
        searchCompany.setCompanyJobList(jobList);
        return companyRespository.save(searchCompany);
    }
}
