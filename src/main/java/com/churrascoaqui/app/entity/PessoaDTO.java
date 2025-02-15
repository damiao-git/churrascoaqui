package com.churrascoaqui.app.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class PessoaDTO {

    @NotBlank
    @NotNull
    @Size(min = 3, max = 50, message = "O tamanho do nome deve ser entre 3 e 50 caracteres")
    private String nome;

    @NotNull(message = "Preencha a data de nascimento")
    private Date dataNascomento;

    @NotBlank(message = "Preencha o CPF")
    @NotNull(message = "O CPF não pode ser nulo")
    private String cpf;
}
