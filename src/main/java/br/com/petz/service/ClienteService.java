package br.com.petz.service;

import br.com.petz.entity.Cliente;
import br.com.petz.entity.Pet;
import br.com.petz.exception.ResourceNotFoundException;
import br.com.petz.repository.ClienteRepository;
import br.com.petz.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final PetRepository petRepository;

    @Transactional(readOnly = true)
    public Cliente findById(final Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("cliente not found"));
    }

    @Transactional(readOnly = true)
    public Page<Cliente> search(final Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<Cliente> listAll() {
        return this.repository.findAll();
    }

    @Transactional
    public Cliente save(final Cliente cliente) {
        return this.repository.save(cliente);
    }

    @Transactional
    public void delete(final Long id) {
        this.repository.deleteById(id);
    }

    @Transactional
    public Cliente update(final Cliente cliente) {
        final var clienteDB = findById(cliente.getId());
        updateData(clienteDB, cliente);
        return this.repository.save(clienteDB);
    }

    @Transactional(readOnly = true)
    public List<Pet> findAllPetsByClient(final Long id) {
        return this.petRepository.findAllByCliente_Id(id);
    }

    @Transactional
    public Pet savePet(final Long id, final Pet pet) {
        final var cliente = findById(id);
        pet.setCliente(cliente);
        return this.petRepository.save(pet);
    }

    protected void updateData(final Cliente clienteDB, final Cliente cliente) {
        clienteDB.setNome(cliente.getNome());
        clienteDB.setEmail(cliente.getEmail());
        clienteDB.setPets(cliente.getPets());
    }

}
