package com.tc3.parquimetro.excecoes;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ValidacaoCampo {
    private String campo;
    private String mensagem;

    public ValidacaoCampo() {}
    public ValidacaoCampo(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }
}
