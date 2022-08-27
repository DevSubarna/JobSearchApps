package edu.miu.ea.cs544.springboot.eaproject.entities;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Application implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-DD-YYYY")
    private String date;
    private int resumeVersion;

    @OneToOne(targetEntity = Job.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "job_fk", referencedColumnName = "id")
    private Job applicationJob;

    public Application(String date, int resumeVersion) {
        this.date = date;
        this.resumeVersion = resumeVersion;
    }

    @Override
    public String toString() {
        return "Application{" +
                "date='" + date + '\'' +
                ", resumeVersion=" + resumeVersion +
                '}';
    }
}
