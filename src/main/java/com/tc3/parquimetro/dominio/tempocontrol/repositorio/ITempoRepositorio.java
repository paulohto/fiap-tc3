package com.tc3.parquimetro.dominio.tempocontrol.repositorio;

import com.tc3.parquimetro.dominio.tempocontrol.entidade.Tempo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITempoRepositorio extends JpaRepository<Tempo, Long> {
}
