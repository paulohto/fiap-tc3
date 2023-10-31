package com.tc3.parquimetro.dominio.veiculo.repositorio;

import com.tc3.parquimetro.dominio.veiculo.entidade.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepositorio extends JpaRepository<Veiculo, Long> {
    Veiculo getByPlaca(String placa);
}
