package org.example;

import org.example.enums.Consolas;
import org.example.enums.OrderDir;
import org.example.enums.OrderType;
import org.example.generator.TiendaGenerator;
import org.example.models.Juego;
import org.example.models.JuegoStock;
import org.example.models.Tienda;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.*;
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
    public void tiendaShouldSearchConsoles() {
        //Arrange
        ArrayList<Consolas> consoles = new ArrayList<>();
        consoles.add(Consolas.PC);
        consoles.add(Consolas.SWITCH);
        //Act
        var gamesSearched = tienda.getGames().searchGames(consoles).stream().sorted(Comparator.comparing(JuegoStock::getConsole)).collect(Collectors.toList());
        //Assert
        Assertions.assertTrue(checkConsoles(consoles, gamesSearched));
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

    @ParameterizedTest
    @EnumSource(OrderType.class)
    public void tiendaShouldOrder(OrderType orderType) {
        //Act
        var gamesSearched = tienda.getGames().orderGames(orderType,OrderDir.ASC);
        var gamesSearchedDesc = tienda.getGames().orderGames(orderType,OrderDir.DESC);
        //Assert
        Assertions.assertAll(
                () -> Assertions.assertTrue(checkOrder(gamesSearched,orderType,OrderDir.ASC)),
                () -> Assertions.assertTrue(checkOrder(gamesSearchedDesc,orderType,OrderDir.DESC))
        );

    }

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

    private boolean checkConsoles(List<Consolas> consoles, List<JuegoStock> gamesSearched) {
        var consolesFound = gamesSearched.stream().map(Juego::getConsole).distinct().collect(Collectors.toList());
        return consoles.equals(consolesFound);
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

    private boolean checkOrder(ArrayList<JuegoStock> gamesOrdered,OrderType orderType,OrderDir orderDir) {
        List<Object> list;
        switch (orderType) {
            case CONSOLE:
                list = gamesOrdered.stream().map(JuegoStock::getConsole).collect(Collectors.toList());
                break;
            case TITLE:
                list = gamesOrdered.stream().map(JuegoStock::getTitle).collect(Collectors.toList());
                break;
            case PRICE:
                list = gamesOrdered.stream().map(JuegoStock::getPrice).collect(Collectors.toList());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + orderType);
        }
        List<Object> sortedList = list.stream().sorted().collect(Collectors.toList());

        if(orderDir == OrderDir.DESC)
            Collections.reverse(sortedList);

        return sortedList.equals(list);

    }

    @BeforeEach
    public void reset() {
        tienda = TiendaGenerator.generateShop(TIENDA_GAMES_COUNT);
        int index = TiendaGenerator.getRandomInt(TIENDA_GAMES_COUNT);
        gameToSearch = tienda.getGames().get(index);
    }
}