package com.churrascoaqui.app.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class ChurrascoDTO {

    @NotBlank
    @Size(min=3, max=50, message = "O local deve conter entre 3 e 50 caracteres")
    private String local;

    @NotNull(message = "A data não pode ser nula")
    private Date data;
}
