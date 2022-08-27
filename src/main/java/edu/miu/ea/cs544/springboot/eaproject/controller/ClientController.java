package edu.miu.ea.cs544.springboot.eaproject.controller;

import edu.miu.ea.cs544.springboot.eaproject.entities.Client;
import edu.miu.ea.cs544.springboot.eaproject.service.CompanyService;
import org.springframework.beans.CachedIntrospectionResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portal")
public class ClientController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/client/{id}")
    public Client findClientById(@PathVariable int id) {
        return companyService.findClientById(id);
    }

    @PostMapping("/client/new")
    public Client addNewClient(@RequestBody Client client) {
        return companyService.saveClient(client);
    }

    @PutMapping("/client/edit/{id}")
    public Client editClient(@PathVariable int id, @RequestBody Client client) {
        return companyService.editClient(id, client);
    }

    @DeleteMapping("/client/delete/{id}")
    public void deleteClientById(@PathVariable int id) {
        companyService.deleteClientById(id);
    }
}
