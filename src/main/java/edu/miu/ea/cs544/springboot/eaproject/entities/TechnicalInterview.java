package edu.miu.ea.cs544.springboot.eaproject.entities;

import edu.miu.ea.cs544.springboot.eaproject.constants.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class TechnicalInterview extends Interview {
    private String duration;
    private Location location;
    private String Questions;
}
