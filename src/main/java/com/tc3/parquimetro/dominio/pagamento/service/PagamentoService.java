package com.tc3.parquimetro.dominio.pagamento.service;

import com.tc3.parquimetro.dominio.pagamento.dto.PagamentoDto;
import com.tc3.parquimetro.dominio.pagamento.entidade.Pagamento;
import com.tc3.parquimetro.dominio.pagamento.repositorio.PagamentoRepositorio;
import com.tc3.parquimetro.excecoes.ControllerNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepositorio repoPagamento;

    @Transactional
    public PagamentoDto save(PagamentoDto pagamento){
        Pagamento entidade = new Pagamento();
        mapperDtoParaEntidade(pagamento, entidade);
        var pagamentoSalvo = repoPagamento.save(entidade);
        return new PagamentoDto(pagamentoSalvo);
    }

    @Transactional
    public PagamentoDto getPagamento(String placa){
        try {
            Pagamento entidade = repoPagamento.getByPlaca(placa);
            //Todo logica para buscar um pagamento.

            return new PagamentoDto(entidade);
        } catch(EntityNotFoundException e){
            throw new ControllerNotFoundException("Veiculo não encontrado, placa: " + placa);
        }
    }

    public void delete(Long id){}

    private void mapperDtoParaEntidade(PagamentoDto dto, Pagamento entidade){
        entidade.setCondutor(dto.getCondutor());
        entidade.setVeiculo(dto.getVeiculo());
        entidade.setFormaDePagamento(dto.getFormaDePagamento());
    }
}
