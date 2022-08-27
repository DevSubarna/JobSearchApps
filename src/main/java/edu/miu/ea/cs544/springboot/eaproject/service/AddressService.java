package edu.miu.ea.cs544.springboot.eaproject.service;

import edu.miu.ea.cs544.springboot.eaproject.entities.Address;
import edu.miu.ea.cs544.springboot.eaproject.exception.NotFoundException;
import edu.miu.ea.cs544.springboot.eaproject.persistence.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

//@Service
@Component
@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address saveAddress(String street, String city, String state, String zipcode) {
        Address newAddress = new Address(street, city,state,zipcode);
        return addressRepository.save(newAddress);
    }

    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address editAddress(int id, Address address) {
        Optional<Address> editAddress = addressRepository.findById(id);
        if(editAddress.isPresent()) {
            Address tempAddress = editAddress.get();
            tempAddress.setStreet(address.getStreet());
            tempAddress.setCity(address.getCity());
            tempAddress.setState(address.getState());
            return addressRepository.save(tempAddress);
        } else {
            throw new NotFoundException("Address " + id + " not found");
        }
    }

    public void deleteAddress(int id) {
        addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Address " + id + " not found"));
        addressRepository.deleteById(id);
    }

    public Address findById(int id) {
        Optional<Address> address = addressRepository.findById(id);
        if(address.isPresent()) {
            return address.get();
        } else {
            throw new NotFoundException("Address " + id + " not found");
        }
    }
}
