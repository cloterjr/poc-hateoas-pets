package br.com.petz.controller;

import br.com.petz.dto.PetDTO;
import br.com.petz.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService service;

    @GetMapping("/{id}")
    public ResponseEntity<PetDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new PetDTO(this.service.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<PetDTO>> listAll() {
        final var dtos = this.service.listAll()
                .stream()
                .map(PetDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

}
