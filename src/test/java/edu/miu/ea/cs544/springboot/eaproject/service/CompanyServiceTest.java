package edu.miu.ea.cs544.springboot.eaproject.service;

import edu.miu.ea.cs544.springboot.eaproject.entities.Client;
import edu.miu.ea.cs544.springboot.eaproject.exception.NotFoundException;
import edu.miu.ea.cs544.springboot.eaproject.persistence.ClientRepository;
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
public class CompanyServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private CompanyService companyService;

    private Client client;

    @Before
    public void setup() {
        client = new Client("Mobile development","software","www.samsung.com");
    }

    @After
    public void tearDown() {
        client = null;
    }

    @Test
    public void createClientTest() {
        when(clientRepository.save(ArgumentMatchers.any(Client.class))).thenReturn(client);
        Client created = companyService.saveClient(client);
        assertThat(created.getMission()).isSameAs(client.getMission());
        verify(clientRepository).save(client);
    }

    @Test
    public void findClientById() {
        when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));
        Client expected = companyService.findClientById(client.getId());
        assertThat(expected).isSameAs(client);
        verify(clientRepository).findById(client.getId());
    }

    @Test(expected = NotFoundException.class)
    public void findClientByIdNotExists() {
        given(clientRepository.findById(anyInt())).willReturn(Optional.ofNullable(null));
        companyService.findClientById(client.getId());
    }

    @Test
    public void del_whenGivenId_shouldDeleteClient_ifFound(){
        when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));
        companyService.deleteClientById(client.getId());
        verify(clientRepository).deleteById(client.getId());
    }

    @Test(expected = NotFoundException.class)
    public void del_should_throw_exception_when_delete_client_doesnt_exist() {
        given(clientRepository.findById(anyInt())).willReturn(Optional.ofNullable(null));
        companyService.deleteClientById(client.getId());
    }
}
