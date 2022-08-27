package edu.miu.ea.cs544.springboot.eaproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Recruiter extends Company {

    @OneToMany(targetEntity = Client.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_id", referencedColumnName = "id")
    @JsonIgnore
    private List<Client> clientList;

    public Recruiter(String name, Address companyAddress, List<Client> clientList) {
        super(name, companyAddress);
        this.clientList = clientList;
    }
}
