package edu.miu.ea.cs544.springboot.eaproject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class HiringManagerInterview extends Interview {
    private int teamSize;
    private LocalTime startDate;
}
