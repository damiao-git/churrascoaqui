package com.churrascoaqui.app.repository;

import com.churrascoaqui.app.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
