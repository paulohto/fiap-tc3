package com.tc3.parquimetro.dominio.tempocontrol.repositorio;

import com.tc3.parquimetro.dominio.tempocontrol.entidade.Tempo;
import com.tc3.parquimetro.dominio.tempocontrol.entidade.TempoAdd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITempoAddRepositorio extends JpaRepository<TempoAdd, Long> {
}

