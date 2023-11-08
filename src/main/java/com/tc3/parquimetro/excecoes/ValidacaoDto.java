package com.tc3.parquimetro.excecoes;

import java.util.ArrayList;
import java.util.List;

public class ValidacaoDto extends DefaultError{
    private List<ValidacaoCampo> mensagens = new ArrayList<ValidacaoCampo>();
    public List<ValidacaoCampo> getMensagens() {
        return mensagens;
    }
    public void  addMenssagens(String campo, String mensagem) {
        mensagens.add(new ValidacaoCampo(campo, mensagem));
    }
}
