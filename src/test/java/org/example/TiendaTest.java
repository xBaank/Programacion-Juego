package org.example;

import org.example.enums.Consolas;
import org.example.generator.TiendaGenerator;
import org.example.models.Juego;
import org.example.models.JuegoStock;
import org.example.models.Tienda;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collectors;

public class TiendaTest {
    private Tienda tienda;
    private JuegoStock gameToSearch;

    private final int TIENDA_GAMES_COUNT = 50;

    @Test
    public void tiendaShouldSearchTitle() {
        //Arrange
        var title = gameToSearch.getTitle();
        //Act
        ArrayList<JuegoStock> gamesSearched = tienda.getGames().searchGames(title);
        //Assert
        Assertions.assertTrue(checkTitle(gameToSearch.getTitle(), gamesSearched));
    }

    @Test
    public void tiendaShouldSearchConsole() {
        //Arrange
        var console = gameToSearch.getConsole();
        //Act
        var gamesSearched = tienda.getGames().searchGames(console);
        //Assert
        Assertions.assertTrue(checkConsole(console, gamesSearched));
    }

    @Test
    public void tiendaShouldSearchPrice() {
        //Arrange
        float maxPrice = 100;
        float minPrice = 50;
        //Act
        var gamesSearched = tienda.getGames().searchGames(minPrice, maxPrice);
        //Assert

        Assertions.assertTrue(checkPrice(minPrice, maxPrice, gamesSearched));
    }

    @Test
    public void tiendaShouldSearch() {
        //Arrange
        float maxPrice = gameToSearch.getPrice();
        float minPrice = gameToSearch.getPrice();
        //Act
        var gamesSearched = tienda.getGames().searchGames(gameToSearch.getTitle(), gameToSearch.getConsole(), minPrice, maxPrice);
        //Assert
        Assertions.assertAll(
                () -> Assertions.assertTrue(checkTitle(gameToSearch.getTitle(), gamesSearched)),
                () -> Assertions.assertTrue(checkConsole(gameToSearch.getConsole(), gamesSearched)),
                () -> Assertions.assertTrue(checkPrice(minPrice, maxPrice, gamesSearched))
        );
    }

//    @Test
//    public void tiendaShouldOrder() {
//        //Arrange
//        float maxPrice = gameToSearch.getPrice();
//        float minPrice = gameToSearch.getPrice();
//        //Act
//        var gamesSearched = tienda.getGames().searchGames(gameToSearch.getTitle(), gameToSearch.getConsole(), minPrice, maxPrice);
//        //Assert
//        Assertions.assertAll(
//                () -> Assertions.assertTrue(checkTitle(gameToSearch.getTitle(), gamesSearched)),
//                () -> Assertions.assertTrue(checkConsole(gameToSearch.getConsole(), gamesSearched)),
//                () -> Assertions.assertTrue(checkPrice(minPrice, maxPrice, gamesSearched))
//        );
//    }

    private boolean checkTitle(String title, ArrayList<JuegoStock> gamesSearched) {
        boolean isGameSearched = true;
        for (JuegoStock gameFound : gamesSearched) {
            if (!gameFound.getTitle().toUpperCase(Locale.ROOT).contains(title.toUpperCase(Locale.ROOT))) {
                isGameSearched = false;
                break;
            }
        }
        return isGameSearched;
    }

    private boolean checkConsole(Consolas console, ArrayList<JuegoStock> gamesSearched) {
        var consolesFound = gamesSearched.stream().map(Juego::getConsole).distinct().collect(Collectors.toList());
        return consolesFound.size() == 1 && consolesFound.stream().findFirst().get() == console;
    }

    private boolean checkPrice(float minPrice, float maxPrice, ArrayList<JuegoStock> gamesSearched) {
        boolean isPricesAreInRange = true;
        for (JuegoStock juegoStock : gamesSearched) {
            if (minPrice > juegoStock.getPrice() || maxPrice < juegoStock.getPrice()) {
                isPricesAreInRange = false;
                break;
            }
        }
        return isPricesAreInRange;
    }

    @BeforeEach
    public void reset() {
        tienda = TiendaGenerator.generateShop(TIENDA_GAMES_COUNT);
        int index = TiendaGenerator.getRandomInt(TIENDA_GAMES_COUNT);
        gameToSearch = tienda.getGames().get(index);
    }
}