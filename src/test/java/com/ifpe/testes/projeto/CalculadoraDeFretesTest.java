package com.ifpe.testes.projeto;

/*
    IFPE - Campus Jaboatão
    Projeto - Testes de Software
    Docente: Emanuel Dantas Filho
    Discente(s): Heitor Fidelis, Leandro Ivanildo
*/

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class CalculadoraDeFretesTest {

    private final CalculadoraDeFretes calculadora = new CalculadoraDeFretes();

    // Cenário 1: Frete gratuito
    @ParameterizedTest
    @CsvSource({
        "150, 0, 0.0",
        "101, 5, 0.0",
        "500, 100, 0.0",
        "100.01, 1, 0.0",
        "200, 1000, 0.0",
        "120, 0.5, 0.0",
        "300, 25, 0.0",
        "999, 99, 0.0",
        "101.5, 7, 0.0",
        "150, 3.3, 0.0"
    })
    void testFreteGratuito(double valorCompra, double kmEntrega, double esperado) {
        assertEquals(esperado, calculadora.calcularFrete(valorCompra, kmEntrega));
    }

    // Cenário 2: Frete com 50% de desconto
    @ParameterizedTest
    @CsvSource({
        "70, 10, 7.5",
        "80, 5, 6.25",
        "100, 20, 10.0",
        "75.5, 8, 7.0",
        "95, 0, 5.0",
        "99.99, 2, 5.5",
        "100, 0.5, 5.125",
        "70, 50, 17.5",
        "89.9, 12, 8.0",
        "90, 100, 30.0"
    })
    void testFreteDesconto(double valorCompra, double kmEntrega, double esperado) {
        assertEquals(esperado, calculadora.calcularFrete(valorCompra, kmEntrega));
    }

    // Cenário 3: Frete padrão
    @ParameterizedTest
    @CsvSource({
        "69.99, 1, 10.5",
        "10, 0, 10.0",
        "50, 5, 12.5",
        "1, 1, 10.5",
        "69, 2, 11.0",
        "0.01, 10, 15.0",
        "20, 3.5, 11.75",
        "69.98, 100, 60.0",
        "60, 7, 13.5",
        "69.99, 0, 10.0"
    })
    void testFreteNormal(double valorCompra, double kmEntrega, double esperado) {
        assertEquals(esperado, calculadora.calcularFrete(valorCompra, kmEntrega));
    }

    // Cenário 4: Exceções - valorCompra <= 0
    private static Stream<Arguments> entradasTestValorCompraInvalido() {
        return Stream.of(
            Arguments.of(0, 10),
            Arguments.of(-10, 0),
            Arguments.of(-1, 10),
            Arguments.of(-999, 5),
            Arguments.of(0, 0),
            Arguments.of(-0.01, 1),
            Arguments.of(-100, 0.1),
            Arguments.of(-50, 99),
            Arguments.of(-2, 2),
            Arguments.of(0, 100)
        );
    }

    @ParameterizedTest
    @MethodSource("entradasTestValorCompraInvalido")
    void testValorCompraInvalido(double valorCompra, double kmEntrega) {
        assertThrows(ValorCompraInvalidoException.class, () -> calculadora.calcularFrete(valorCompra, kmEntrega));
    }

    // Cenário 5: Exceções - kmEntrega < 0
    private static Stream<Arguments> entradasTestKmEntregaInvalido() {
        return Stream.of(
            Arguments.of(80, -1),
            Arguments.of(150, -0.01),
            Arguments.of(60, -10),
            Arguments.of(200, -100),
            Arguments.of(90, -2),
            Arguments.of(30, -5.5),
            Arguments.of(500, -1000),
            Arguments.of(70, -0.1),
            Arguments.of(100, -9.99),
            Arguments.of(10, -50)
        );
    }

    @ParameterizedTest
    @MethodSource("entradasTestKmEntregaInvalido")
    void testKmEntregaInvalido(double valorCompra, double kmEntrega) {
        assertThrows(KmEntregaInvalidoException.class, () -> calculadora.calcularFrete(valorCompra, kmEntrega));
    }
}
