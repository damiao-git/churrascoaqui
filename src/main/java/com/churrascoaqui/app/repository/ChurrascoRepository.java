package com.churrascoaqui.app.repository;

import com.churrascoaqui.app.entity.Churrasco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ChurrascoRepository extends JpaRepository<Churrasco, Long> {

    @Query("SELECT ch FROM Churrasco ch WHERE " +
            " ch.local LIKE :local " +
            " AND ch.data BETWEEN :dataInicial AND :dataFinal")
    List<Churrasco> filtrar(
            @Param("dataInicial") Date dataInicial,
            @Param("dataFinal") Date dataFinal,
            @Param("local") String local
            );
}
