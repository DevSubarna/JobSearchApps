package edu.miu.ea.cs544.springboot.eaproject.service;

import edu.miu.ea.cs544.springboot.eaproject.entities.Address;
import edu.miu.ea.cs544.springboot.eaproject.exception.NotFoundException;
import edu.miu.ea.cs544.springboot.eaproject.persistence.AddressRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    private Address address;

    @Before
    public void setup() {
        address = new Address(1,"100th","burlington","iowa","1234");
    }

    @After
    public void tearDown() {
        address = null;
    }

    @Test
    public void createAddressTest() {
        when(addressRepository.save(ArgumentMatchers.any(Address.class))).thenReturn(address);
        Address created = addressService.addAddress(address);
        assertThat(created.getStreet()).isSameAs(address.getStreet());
        verify(addressRepository).save(address);
    }

    @Test
    public void findAddressById() {
        when(addressRepository.findById(address.getId())).thenReturn(Optional.of(address));
        Address expected = addressService.findById(address.getId());
        assertThat(expected).isSameAs(address);
        verify(addressRepository).findById(address.getId());
    }

    @Test(expected = NotFoundException.class)
    public void findAddressByIdNotExists() {
        given(addressRepository.findById(anyInt())).willReturn(Optional.ofNullable(null));
        addressService.findById(address.getId());
    }

    @Test
    public void del_whenGivenId_shouldDeleteAddress_ifFound(){
        when(addressRepository.findById(address.getId())).thenReturn(Optional.of(address));
        addressService.deleteAddress(address.getId());
        verify(addressRepository).deleteById(address.getId());
    }

    @Test(expected = NotFoundException.class)
    public void del_should_throw_exception_when_delete_address_doesnt_exist() {
        given(addressRepository.findById(anyInt())).willReturn(Optional.ofNullable(null));
        addressService.deleteAddress(address.getId());
    }
}
