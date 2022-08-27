package edu.miu.ea.cs544.springboot.eaproject.service;

import edu.miu.ea.cs544.springboot.eaproject.entities.Job;
import edu.miu.ea.cs544.springboot.eaproject.exception.NotFoundException;
import edu.miu.ea.cs544.springboot.eaproject.persistence.JobRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class JobServiceTest {

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobService jobService;

    private Job job;

    @Before
    public void setup() {
        job = new Job("Software Devloper 1",80000);
    }

    @After
    public void tearDown() {
        job = null;
    }

    @Test
    public void createJobTest() {
        when(jobRepository.save(ArgumentMatchers.any(Job.class))).thenReturn(job);
        Job created = jobService.createJob(job);
        assertThat(created.getTitle()).isSameAs(job.getTitle());
        verify(jobRepository).save(job);
    }

    @Test
    public void findJobById() {
        when(jobRepository.findById(job.getId())).thenReturn(Optional.of(job));
        Job expected = jobService.findJobById(job.getId());
        assertThat(expected).isSameAs(job);
        verify(jobRepository).findById(job.getId());
    }

    @Test(expected = NotFoundException.class)
    public void findJobByIdNotExists() {
        given(jobRepository.findById(anyInt())).willReturn(Optional.ofNullable(null));
        jobService.findJobById(job.getId());
    }

    @Test
    public void del_whenGivenId_shouldDeleteJob_ifFound(){
        when(jobRepository.findById(job.getId())).thenReturn(Optional.of(job));
        jobService.deleteJob(job.getId());
        verify(jobRepository).deleteById(job.getId());
    }

    @Test(expected = NotFoundException.class)
    public void del_should_throw_exception_when_delete_job_doesnt_exist() {
        given(jobRepository.findById(anyInt())).willReturn(Optional.ofNullable(null));
        jobService.deleteJob(job.getId());
    }
}
