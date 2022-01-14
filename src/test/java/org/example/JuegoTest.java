package org.example;

import org.example.exceptions.JuegoException;
import org.example.models.Juego;
import org.example.testData.JuegosData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class JuegoTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void should_throw_exception() {
        Assertions.assertAll(
                () -> Assertions.assertThrowsExactly(JuegoException.class, JuegosData.RANGE_OVER_LIMIT::run),
                () -> Assertions.assertThrowsExactly(JuegoException.class, JuegosData.RANGE_UNDER_LIMIT::run)
                );
    }
}
