package edu.miu.ea.cs544.springboot.eaproject.controller;

import edu.miu.ea.cs544.springboot.eaproject.entities.Address;
import edu.miu.ea.cs544.springboot.eaproject.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portal")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/address/{id}")
    public Address findAddressById(@PathVariable int id) {
        return addressService.findById(id);
    }

    @PostMapping("/address/create")
    public Address addNewAddress(@RequestBody Address address) {
        return addressService.addAddress(address);
    }

    @PutMapping("/address/edit/{id}")
    public Address editAddress(@PathVariable int id, @RequestBody Address address) {
        return addressService.editAddress(id, address);
    }

    @DeleteMapping("/address/delete/{id}")
    public void deleteAddressById(@PathVariable int id) {
        addressService.deleteAddress(id);
    }
}
