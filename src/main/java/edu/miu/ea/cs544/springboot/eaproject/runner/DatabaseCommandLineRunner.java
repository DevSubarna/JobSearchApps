package edu.miu.ea.cs544.springboot.eaproject.runner;


import edu.miu.ea.cs544.springboot.eaproject.entities.*;
import edu.miu.ea.cs544.springboot.eaproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class DatabaseCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private JobService jobService;

    @Autowired
    private SkillSetService skillSetService;

    @Autowired
    private AddressService addressService;

    private List<Skill> skillList = new ArrayList<>();
    private List<Job> jobList = new ArrayList<>();

    private List<Address> addressList = new ArrayList<>();

    private List<Client> clientList = new ArrayList<>();

    private List<Recruiter> recruiterList = new ArrayList<>();

    private List<Application> applicationList = new ArrayList<>();
    private List<Skill> createSkills() {
        Skill skill1 = new Skill("Web dev","8 years","web development","PHP");
        Skill skill2 = new Skill("Java dev","5 years","Spring boot development","JAVA");
        Skill skill3 = new Skill("Backend dev","10 years","Spring development","JAVA");
        Skill skill4 = new Skill("AWS dev","8 years","AWS services","Amazon AWS");

        return Arrays.asList(skill1,skill2,skill3,skill4);
    }

    private List<Job> createJobs() {
        Job job1 = new Job("Software Devloper 1",80000);
        jobService.saveJob(job1);
        jobService.saveSkillSet(job1.getId(),Arrays.asList(skillList.get(0),skillList.get(1)));
        jobService.saveJobApplication(job1.getId(),applicationList.get(0));
        jobService.saveCompanyInfo(job1.getId(), clientList.get(0));

        Job job2 = new Job("Backend Devloper 2",150000);
        jobService.saveJob(job2);
        jobService.saveSkillSet(job2.getId(),Arrays.asList(skillList.get(1),skillList.get(2)));
        jobService.saveJobApplication(job2.getId(),applicationList.get(1));
        jobService.saveCompanyInfo(job2.getId(), clientList.get(1));

        Job job3 = new Job("Backend Devloper 1",100000);
        jobService.saveJob(job3);
        jobService.saveSkillSet(job3.getId(), Arrays.asList(skillList.get(3),skillList.get(1)));
        jobService.saveJobApplication(job3.getId(),applicationList.get(2));
        jobService.saveCompanyInfo(job3.getId(), clientList.get(2));

        Job job4 = new Job("Frontend Devloper 1",70000);
        jobService.saveJob(job4);
        jobService.saveSkillSet(job4.getId(), Arrays.asList(skillList.get(0),skillList.get(2)));
        jobService.saveJobApplication(job4.getId(),applicationList.get(3));
        jobService.saveCompanyInfo(job4.getId(), clientList.get(3));

       return Arrays.asList(job1,job2,job3,job4);
    }

    private List<Address> createAddresses() {
        Address address1 = new Address("100th","burlington","iowa","1234");
        Address address2 = new Address("500","North Avenue","california","5236");
        Address address3 = new Address("North 1233","Mount Pleasant","iowa","45789");
        Address address4 = new Address("150th","austin","texas","14578");
        Address address5 = new Address("50 North","dallas","texas","45789");
        Address address6 = new Address("80 South","iowa state","iowa","45896");
        return Arrays.asList(address1,address2,address3,address4,address5,address6);
    }

    private List<Client> createClients() {
        Client client1 = new Client("Mobile development","software","www.samsung.com");
        client1.setName("Samsung");
        client1.setCompanyAddress(addressList.get(0));

        Client client2 = new Client("Equipments","hardware","www.philips.com");
        client2.setName("Philips");
        client2.setCompanyAddress(addressList.get(1));

        Client client3 = new Client("Mobile development","software","www.apple.com");
        client3.setName("Apple");
        client3.setCompanyAddress(addressList.get(2));

        Client client4 = new Client("App development","software","www.microsoft.com");
        client4.setName("Microsoft");
        client4.setCompanyAddress(addressList.get(3));

        return Arrays.asList(client1,client2,client3,client4);
    }

    public List<Recruiter> createRecruiters() {
        Recruiter recruiter1 = new Recruiter("Recruiter1",addressList.get(4),Arrays.asList(clientList.get(0),clientList.get(1)));
        Recruiter recruiter2 = new Recruiter("Recruiter2",addressList.get(5),Arrays.asList(clientList.get(2), clientList.get(3)));
        companyService.saveRecruiter(recruiter1);
        companyService.saveRecruiter(recruiter2);

        return Arrays.asList(recruiter1,recruiter2);
    }

    private List<Application> createApplications() {
        Application application1 = new Application("8-8-2022",1);
        Application application2 = new Application("10-8-2022",2);
        Application application3 = new Application("12-8-2022",3);
        Application application4 = new Application("18-8-2022",4);
        return Arrays.asList(application1,application2,application3,application4);
    }


    @Override
    public void run(String... args) throws Exception {
        addressList = createAddresses();
        clientList = createClients();
        recruiterList = createRecruiters();
        skillList = createSkills();
        applicationList = createApplications();
        jobList = createJobs();
    }
}
