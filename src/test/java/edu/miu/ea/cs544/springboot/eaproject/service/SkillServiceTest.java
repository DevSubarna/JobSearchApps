package edu.miu.ea.cs544.springboot.eaproject.service;

import edu.miu.ea.cs544.springboot.eaproject.entities.Skill;
import edu.miu.ea.cs544.springboot.eaproject.exception.NotFoundException;
import edu.miu.ea.cs544.springboot.eaproject.persistence.SkillRepository;
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
public class SkillServiceTest {

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillSetService skillSetService;

    private Skill skill;

    @Before
    public void setup() {
        skill = new Skill("AWS dev","8 years","AWS services","Amazon AWS");
    }

    @After
    public void tearDown() {
        skill = null;
    }

    @Test
    public void createSkillTest() {
        when(skillRepository.save(ArgumentMatchers.any(Skill.class))).thenReturn(skill);
        Skill created = skillSetService.addSkill(skill);
        assertThat(created.getName()).isSameAs(skill.getName());
        verify(skillRepository).save(skill);
    }

    @Test
    public void findSkillById() {
        when(skillRepository.findById(skill.getId())).thenReturn(Optional.of(skill));
        Skill expected = skillSetService.findSkillById(skill.getId());
        assertThat(expected).isSameAs(skill);
        verify(skillRepository).findById(skill.getId());
    }

    @Test(expected = NotFoundException.class)
    public void findSkillByIdNotExists() {
        given(skillRepository.findById(anyInt())).willReturn(Optional.ofNullable(null));
        skillSetService.findSkillById(skill.getId());
    }

    @Test
    public void del_whenGivenId_shouldDeleteSkill_ifFound(){
        when(skillRepository.findById(skill.getId())).thenReturn(Optional.of(skill));
        skillSetService.deleteSkillById(skill.getId());
        verify(skillRepository).deleteById(skill.getId());
    }

    @Test(expected = NotFoundException.class)
    public void del_should_throw_exception_when_delete_skill_doesnt_exist() {
        given(skillRepository.findById(anyInt())).willReturn(Optional.ofNullable(null));
        skillSetService.deleteSkillById(skill.getId());
    }
}
