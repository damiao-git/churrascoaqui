package com.churrascoaqui.app.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "churrascos")
@Data
public class Churrasco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String local;
    private Date data;

    @ManyToMany
    @JoinTable(
            name = "churrasco_pessoa",
            joinColumns = @JoinColumn(name = "churrasco_id"),
            inverseJoinColumns = @JoinColumn(name = "pessoa_id")
    )
    private Set<Pessoa> pessoas = new HashSet<Pessoa>();

}
