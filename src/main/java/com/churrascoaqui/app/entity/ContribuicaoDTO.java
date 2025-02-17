package com.churrascoaqui.app.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ContribuicaoDTO {
    @NotNull
    @NotBlank
    private String item;

    @NotNull
    private Churrasco churrasco;

    @NotNull
    private Pessoa pessoa;
}
