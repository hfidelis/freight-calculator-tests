package com.ifpe.testes.projeto;

public class ValorCompraInvalidoException extends IllegalArgumentException {
    public ValorCompraInvalidoException() {
        super("Valor da compra deve ser maior que zero.");
    }
}