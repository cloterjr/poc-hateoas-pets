package br.com.petz.dto;

import br.com.petz.controller.ClienteController;
import br.com.petz.entity.Cliente;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
@NoArgsConstructor
public class ClienteDTO extends RepresentationModel<ClienteDTO> {

    private Long id;
    private String nome;
    private String email;

    public ClienteDTO(final Cliente entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.email = entity.getEmail();

        final var linkSelf = linkTo(methodOn(ClienteController.class)
                .findById(entity.getId()))
                .withSelfRel();
        add(linkSelf);

        final var linkListPets = linkTo(methodOn(ClienteController.class)
                .findAllPetsByClient(entity.getId()))
                .withRel("all-pets");
        add(linkListPets);
    }

    public Cliente toEntity() {
        final Cliente cliente = new Cliente();
        cliente.setEmail(this.email);
        cliente.setNome(this.getNome());
        cliente.setId(this.id);
        cliente.getPets().forEach(pet -> pet.setCliente(cliente));
        return cliente;
    }

}
