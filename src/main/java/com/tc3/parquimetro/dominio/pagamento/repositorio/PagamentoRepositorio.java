package com.tc3.parquimetro.dominio.pagamento.repositorio;

import com.tc3.parquimetro.dominio.pagamento.entidade.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepositorio extends JpaRepository<Pagamento, String> {
    Pagamento getByPlaca(String placa);
}
