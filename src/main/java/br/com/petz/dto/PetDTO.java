package br.com.petz.dto;

import br.com.petz.controller.PetController;
import br.com.petz.entity.Pet;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
@NoArgsConstructor
public class PetDTO extends RepresentationModel<PetDTO> {

    private Long id;
    private String nome;
    private ClienteDTO cliente;

    public PetDTO(final Pet entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.cliente = new ClienteDTO(entity.getCliente());

        final var link = linkTo(methodOn(PetController.class)
                .findById(entity.getId()))
                .withSelfRel();
        add(link);
    }

    public Pet toEntity() {
        final Pet pet = new Pet();
        pet.setId(this.id);
        pet.setNome(this.nome);
        return pet;
    }

}
