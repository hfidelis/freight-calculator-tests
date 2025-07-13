package com.ifpe.testes.projeto;

public class KmEntregaInvalidoException extends IllegalArgumentException {
    public KmEntregaInvalidoException() {
        super("Distância da entrega deve ser maior ou igual a zero.");
    }
}
