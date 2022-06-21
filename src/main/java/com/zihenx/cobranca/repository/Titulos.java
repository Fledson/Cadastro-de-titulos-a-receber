package com.zihenx.cobranca.repository;

import com.zihenx.cobranca.model.Titulo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Titulos extends JpaRepository<Titulo, Long> {
  /**
   * Query personalizada usando a personalização do Spring Data
   * 
   * @param descricao - Nome a ser buscado
   * @return retorna uma lista de titulos
   */
  List<Titulo> findByDescricaoContaining(String descricao);
}
