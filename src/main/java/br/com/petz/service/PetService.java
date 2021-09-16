package br.com.petz.service;

import br.com.petz.entity.Pet;
import br.com.petz.exception.ResourceNotFoundException;
import br.com.petz.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    @Transactional(readOnly = true)
    public Pet findById(final Long id) {
        return this.petRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("pet not found"));
    }

    @Transactional(readOnly = true)
    public List<Pet> listAll() {
        return this.petRepository.findAll();
    }

}
