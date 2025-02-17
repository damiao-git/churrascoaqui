package com.churrascoaqui.app.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "contribuicao")
@Data
public class Contribuicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String item;

    @ManyToOne
    @JoinColumn(name = "churrasco_id")
    private Churrasco churrasco;

    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
}
