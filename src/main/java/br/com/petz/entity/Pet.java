package br.com.petz.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

}
