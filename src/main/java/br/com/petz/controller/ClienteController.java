package br.com.petz.controller;

import br.com.petz.dto.ClienteDTO;
import br.com.petz.dto.PetDTO;
import br.com.petz.entity.Cliente;
import br.com.petz.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(new ClienteDTO(this.service.findById(id)));
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<ClienteDTO>> search(Pageable pageable) {
        final Page<Cliente> clientes = this.service.search(pageable);
        final Page<ClienteDTO> dtos = clientes.map(ClienteDTO::new);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listAll() {
        final var dtos = this.service
                .listAll()
                .stream()
                .map(ClienteDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteDTO.toEntity();
        cliente = this.service.save(cliente);

        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/{id}/pets")
    public ResponseEntity<Void> savePet(@PathVariable Long id, @RequestBody PetDTO petDTO) {
        final var pet = this.service.savePet(id, petDTO.toEntity());

        final URI uri = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/api/v1/pets/{id}")
                .buildAndExpand(pet.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody ClienteDTO clienteDTO, @PathVariable("id") Long id) {
        clienteDTO.setId(id);
        this.service.update(clienteDTO.toEntity());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/pets")
    public ResponseEntity<List<PetDTO>> findAllPetsByClient(@PathVariable Long id) {
        final var dtos = this.service.findAllPetsByClient(id)
                .stream()
                .map(PetDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

}
