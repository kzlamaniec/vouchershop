package pl.kzlamaniec.vouchershop.crm;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CrmController {

    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/api/clients")
    public Iterable<Client> client() {
        return clientRepository.findAll();
    }

    @PostMapping("/api/clients")
    public void addClient(@Valid @RequestBody Client client) {
        clientRepository.save(client);
    }

    @DeleteMapping("/api/clients/{id}")
    public void deleteClient(@PathVariable int id) {
        clientRepository.deleteById(id);
    }

    @PostMapping("/api/clients/{id}")
    public void updateClient (@PathVariable int id, @RequestBody Client client) {
        Client loaded = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("There is no such client!"));

        loaded.setAddress(client.getAddress());
        loaded.setFirstname(client.getFirstname());
        loaded.setLastname(client.getLastname());

        clientRepository.save(loaded);
    }


}
