package edu.miu.ea.cs544.springboot.eaproject.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.eclipse.persistence.annotations.OptimisticLocking;
//import org.eclipse.persistence.annotations.OptimisticLockingType;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedNativeQuery(name = "findJobByApplication",query = "SELECT * from job where ID = (SELECT JOB_ID from application WHERE application.ID = 4)",resultClass = Job.class)
//@OptimisticLocking(type= OptimisticLockingType.VERSION_COLUMN)
@OptimisticLocking(type= OptimisticLockType.VERSION)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Job implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private int salary;

    @Version
    private long version;

    @OneToOne(targetEntity = Application.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "app_fk", referencedColumnName = "id")
    private Application jobApplication;

    @OneToMany(mappedBy = "refJob", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Skill> skillList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_fk")
    private Client companyName;

    public Job(String title, int salary) {
        this.title = title;
        this.salary = salary;
    }

    public void setJobApplication(Application jobApplication) {
        this.jobApplication = jobApplication;
        if(jobApplication.getApplicationJob() == null)
            jobApplication.setApplicationJob(this);
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
        skillList.forEach(skill -> {
            if(skill.getRefJob() == null) {
                skill.setRefJob(this);
            }});
    }

    @Override
    public String toString() {
        return "Job{" +
                "title='" + title + '\'' +
                ", salary=" + salary +
                ", version=" + version +
                '}';
    }
}
