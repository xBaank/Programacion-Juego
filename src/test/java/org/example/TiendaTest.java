package org.example;

import org.example.models.Juego;
import org.example.models.JuegoStock;
import org.example.models.Tienda;
import org.example.utils.JuegoUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

public class TiendaTest {
    private Tienda tienda;
    private JuegoStock gameToSearch;

    private final int TIENDA_GAMES_COUNT = 50;

    @Test
    public void tiendaShouldSearchTitle() {
        //Act
        JuegoStock gameSearched = tienda.searchGames(gameToSearch.getTitle())
                .stream().findFirst().get();
        //Assert
        Assertions.assertEquals(gameSearched,gameToSearch);
    }

    @Test
    public void tiendaShouldSearchConsole() {
        //Arrange
        var console = gameToSearch.getConsole();
        //Act
        var gamesSearched = tienda.searchGames(console);
        var consolesFound = gamesSearched.stream().map(Juego::getConsole).distinct().collect(Collectors.toList());
        //Assert
        boolean isConsoleFound = consolesFound.size() == 1 && consolesFound.stream().findFirst().get() == console;
        Assertions.assertTrue(isConsoleFound);
    }

    @Test
    public void tiendaShouldSearchPrice() {
        //Arrange
        float maxPrice = 100;
        float minPrice = 50;
        //Act
        var gamesSearched = tienda.searchGames(minPrice,maxPrice);
        //Assert
        boolean isPricesAreInRange = true;
        for (JuegoStock juegoStock : gamesSearched) {
            if(minPrice > juegoStock.getPrice() || maxPrice < juegoStock.getPrice()) {
                isPricesAreInRange = false;
                break;
            }
        }
        Assertions.assertTrue(isPricesAreInRange);
    }

    @Test
    public void tiendaShouldSearch() {
        //Arrange
        float maxPrice = 100;
        float minPrice = 50;
        //Act
        var gamesSearched = tienda.searchGames(gameToSearch.getTitle(),gameToSearch.getConsole(),minPrice,maxPrice);
        //Assert

        //Mover a funciones y hacer assert all para comprobar todos los campos
        boolean isPricesAreInRange = true;
        for (JuegoStock juegoStock : gamesSearched) {
            if(minPrice > juegoStock.getPrice() || maxPrice < juegoStock.getPrice()) {
                isPricesAreInRange = false;
                break;
            }
        }
        Assertions.assertTrue(isPricesAreInRange);
    }

    @BeforeEach
    public void reset() {
        tienda = JuegoUtils.generateShop(TIENDA_GAMES_COUNT);
        int index = JuegoUtils.getRandomInt(TIENDA_GAMES_COUNT);
        gameToSearch = tienda.getJuegos().get(index);
    }
}