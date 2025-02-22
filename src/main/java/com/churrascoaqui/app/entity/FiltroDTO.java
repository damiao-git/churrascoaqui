package com.churrascoaqui.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiltroDTO {
    private Date dataInicial;
    private Date dataFinal;
    private String local;

}
