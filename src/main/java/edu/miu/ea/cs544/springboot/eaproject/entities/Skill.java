package edu.miu.ea.cs544.springboot.eaproject.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Skill implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String experience;
    private String description;
    private String language;

    @ManyToOne(targetEntity = Job.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "job_fk", referencedColumnName = "id")
    private Job refJob;

    public Skill(String name, String experience, String description, String language) {
        this.name = name;
        this.experience = experience;
        this.description = description;
        this.language = language;
    }

}
