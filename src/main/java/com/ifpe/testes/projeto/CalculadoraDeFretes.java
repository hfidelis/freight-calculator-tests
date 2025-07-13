package com.ifpe.testes.projeto;

/*
    IFPE - Campus Jaboatão
    Projeto - Testes de Software
    Docente: Emanuel Dantas Filho
    Discente(s): Heitor Fidelis, Leandro Ivanildo
*/

public class CalculadoraDeFretes {
    private static final double FRETE_FIXO = 10;

    public double calcularFrete(
        double valorCompra,
        double kmEntrega
    ) throws ValorCompraInvalidoException, KmEntregaInvalidoException {
        if (valorCompra <= 0) {
            throw new ValorCompraInvalidoException();
        }

        if (kmEntrega < 0) {
            throw new KmEntregaInvalidoException();
        }

        double valorFretePadrao = FRETE_FIXO + (kmEntrega * 0.5);

        if (this.freteGratis(valorCompra)) {
            return 0;
        } else if (this.freteMetade(valorCompra)) {
            return valorFretePadrao / 2;
        } else {
            return valorFretePadrao;
        }
    }

    private boolean freteGratis(double valorCompra) {
        return valorCompra > 100;
    }

    private boolean freteMetade(double valorCompra) {
        return valorCompra >= 70 && valorCompra <= 100;
    }
}
