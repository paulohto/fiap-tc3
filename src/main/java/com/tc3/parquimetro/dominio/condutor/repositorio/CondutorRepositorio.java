package com.tc3.parquimetro.dominio.condutor.repositorio;

import com.tc3.parquimetro.dominio.condutor.entidade.Condutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CondutorRepositorio extends JpaRepository<Condutor, Long> {
    Condutor getByCpf(String cpf);
}
