package edu.miu.ea.cs544.springboot.eaproject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Interview {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalTime date;
    private String phoneNumber;
    private String email;
}
