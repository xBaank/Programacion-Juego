package org.example;

import org.example.exceptions.JuegoException;
import org.example.models.Juego;
import org.example.juegoData.consoleData;
import org.example.juegoData.priceData;
import org.example.juegoData.titleData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Unit test for simple App.
 */
public class JuegoTest {
    public Juego Juego = new Juego();

    @ParameterizedTest
    @ValueSource(floats = {
            priceData.PRICE_OUTSIDE_RANGE_BOTTOM,
            priceData.PRICE_OUTSIDE_RANGE_TOP
    })
    public void priceShouldThrowException(float value) {
        Assertions.assertThrowsExactly(JuegoException.class, () -> Juego.setPrice(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            consoleData.NOT_DEFINED_CONSOLE,
            consoleData.WHITE_SPACED
    })
    @NullSource
    public void consoleShouldThrowException(String value) {
        Assertions.assertThrowsExactly(JuegoException.class, () -> Juego.setConsole(value));
    }

    @ParameterizedTest
    @ValueSource(strings = titleData.EMPTY_VALUE)
    @NullSource
    public void titleShouldThrowException(String value) {
        Assertions.assertThrowsExactly(JuegoException.class, () -> Juego.setTitle(value));
    }

    @ParameterizedTest
    @ValueSource(floats = {
            priceData.PRICE_INSIDE_RANGE_BOTTOM,
            priceData.PRICE_INSIDE_RANGE_TOP,
            priceData.PRICE_NORMAL_VALUE
    })
    public void priceShouldSetAndGet(float value) {
        Juego.setPrice(value);
        Assertions.assertEquals(value, Juego.getPrice());
    }

    @ParameterizedTest
    @ValueSource(strings = consoleData.DEFINED_CONSOLE)
    public void consoleShouldGetAndSet(String value) {
        Juego.setConsole(value);
        Assertions.assertEquals(value, Juego.getConsole().name());
    }

    @ParameterizedTest
    @ValueSource(strings = titleData.NORMAL_VALUE)
    public void titleShouldGetAndSet(String value) {
        Juego.setTitle(value);
        Assertions.assertEquals(value, Juego.getTitle());
    }

    @BeforeEach
    public void reset() {
        Juego = new Juego();
    }

}
