package org.example.models;

import org.example.enums.Consolas;
import org.example.enums.OrderDir;
import org.example.enums.OrderType;

import java.util.*;
import java.util.stream.Collectors;

public class ArrayListGames extends ArrayList<JuegoStock> {

    public ArrayListGames() {
        super();
    }

    public ArrayListGames(ArrayList<JuegoStock> games) {
        super(games);
    }

    public ArrayListGames(List<JuegoStock> games) {
        super(games);
    }

    public ArrayListGames searchGames(String text, List<Consolas> consoles, float minPrice, float MaxPrice) {
        List<JuegoStock> results = getResults(text, consoles, minPrice, MaxPrice);
        return toArrayList(results);
    }

    public ArrayListGames searchGames(String text, Consolas consoles, float minPrice, float MaxPrice) {
        List<JuegoStock> results = getResults(text, List.of(consoles), minPrice, MaxPrice);
        return toArrayList(results);
    }

    public ArrayListGames searchGames(String text) {
        return searchGames(text, Consolas.valuesAsList(), JuegoStock.MIN_PRICE, JuegoStock.MAX_PRICE);
    }

    public ArrayListGames searchGames(Consolas console) {
        ArrayList<Consolas> consoles = new ArrayList<>();
        consoles.add(console);
        return searchGames(null, consoles, JuegoStock.MIN_PRICE, JuegoStock.MAX_PRICE);

    }

    public ArrayListGames searchGames(float minPrice, float maxPrice) {
        return searchGames(null, Consolas.valuesAsList(), minPrice, maxPrice);

    }

    public ArrayListGames orderGames(OrderType orderType, OrderDir orderDir) {
        List<JuegoStock> results;
        switch (orderType) {
            case TITLE:
                results = super.stream().sorted(Comparator.comparing(JuegoStock::getTitle)).collect(Collectors.toList());
                break;
            case PRICE:
                results = super.stream().sorted(Comparator.comparing(JuegoStock::getPrice)).collect(Collectors.toList());
                break;
            case CONSOLE:
                results = super.stream().sorted(Comparator.comparing(JuegoStock::getConsole)).collect(Collectors.toList());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + orderType);
        }

        if (orderDir == OrderDir.DESC)
            Collections.reverse(results);

        if (results.size() > 0)
            return new ArrayListGames(results);
        else
            return new ArrayListGames();
    }

    private List<JuegoStock> getResults(String title, List<Consolas> consoles, float minPrice, float maxPrice) {
        return super.stream().filter(i ->
                (title == null || i.getTitle().toUpperCase(Locale.ROOT).contains(title.toUpperCase(Locale.ROOT))) &&
                        consoles.contains(i.getConsole()) &&
                        (i.getPrice() >= minPrice && i.getPrice() <= maxPrice)

        ).collect(Collectors.toList());
    }


    private ArrayListGames toArrayList(List<JuegoStock> juegoStocks) {
        return new ArrayListGames(juegoStocks);
    }
}
