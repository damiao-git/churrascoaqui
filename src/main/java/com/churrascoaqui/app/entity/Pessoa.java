package com.churrascoaqui.app.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "pessoas")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Date dataNascimento;
    private String cpf;

    @ManyToMany(
            mappedBy = "pessoas"
    )
    private Set<Churrasco> churrascos = new HashSet<Churrasco>();
}
