package edu.miu.ea.cs544.springboot.eaproject.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "COMPANY_TYPE")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public abstract class Company implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addr_fk", referencedColumnName = "id")
    private Address companyAddress;

    @OneToMany(mappedBy ="companyName", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Job> companyJobList;

    public Company(String name, Address address) {
        this.name = name;
        this.companyAddress = address;
    }
}
