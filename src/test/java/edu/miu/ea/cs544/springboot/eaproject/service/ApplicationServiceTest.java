package edu.miu.ea.cs544.springboot.eaproject.service;

import edu.miu.ea.cs544.springboot.eaproject.entities.Application;
import edu.miu.ea.cs544.springboot.eaproject.exception.NotFoundException;
import edu.miu.ea.cs544.springboot.eaproject.persistence.ApplicationRepository;
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
public class ApplicationServiceTest {

    @Mock
    private ApplicationRepository applicationRepository;

    @InjectMocks
    private ApplicationService applicationService;

    private Application application;

    @Before
    public void setup() {
        application = new Application("8-8-2022",1);
    }

    @After
    public void tearDown() {
        application = null;
    }

    @Test
    public void createApplicationTest() {
        when(applicationRepository.save(ArgumentMatchers.any(Application.class))).thenReturn(application);
        Application created = applicationService.addApplication(application);
        assertThat(created.getResumeVersion()).isSameAs(application.getResumeVersion());
        verify(applicationRepository).save(application);
    }

    @Test
    public void findApplicationById() {
        when(applicationRepository.findById(application.getId())).thenReturn(Optional.of(application));
        Application expected = applicationService.findApplicationById(application.getId());
        assertThat(expected).isSameAs(application);
        verify(applicationRepository).findById(application.getId());
    }

    @Test(expected = NotFoundException.class)
    public void findApplicationByIdNotExists() {
        given(applicationRepository.findById(anyInt())).willReturn(Optional.ofNullable(null));
        applicationService.findApplicationById(application.getId());
    }

    @Test
    public void del_whenGivenId_shouldDeleteApplication_ifFound(){
        when(applicationRepository.findById(application.getId())).thenReturn(Optional.of(application));
        applicationService.deleteApplicationById(application.getId());
        verify(applicationRepository).deleteById(application.getId());
    }

    @Test(expected = NotFoundException.class)
    public void del_should_throw_exception_when_delete_application_doesnt_exist() {
        given(applicationRepository.findById(anyInt())).willReturn(Optional.ofNullable(null));
        applicationService.deleteApplicationById(application.getId());
    }
}
