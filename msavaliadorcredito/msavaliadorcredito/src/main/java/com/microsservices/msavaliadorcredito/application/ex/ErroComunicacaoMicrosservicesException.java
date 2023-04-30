package com.microsservices.msavaliadorcredito.application.ex;

import lombok.Getter;

public class ErroComunicacaoMicrosservicesException extends Exception{

    @Getter
    private Integer status;
    public ErroComunicacaoMicrosservicesException(String mensagem, Integer status) {
        super(mensagem);
        this.status = status;
    }
}
