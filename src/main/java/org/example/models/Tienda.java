package org.example.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Tienda {
    private String nombre;
    private ArrayList<JuegoStock> juegos;

    public Tienda() {    }

    public  Tienda(String nombre,ArrayList<JuegoStock> juegos) {
        setNombre(nombre);
        setJuegos(juegos);
    }

    public ArrayList<JuegoStock> getJuegos() {
        return juegos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setJuegos(ArrayList<JuegoStock> juegos) {
        this.juegos = juegos;
    }

    public ArrayList<JuegoStock> searchGames(String text,List<Consolas> consoles,float minPrice,float MaxPrice) {
        List<JuegoStock> results = getResults(text,consoles,minPrice,MaxPrice);
        return toArrayList(results);
    }

    public ArrayList<JuegoStock> searchGames(String text,Consolas consoles,float minPrice,float MaxPrice) {
        List<JuegoStock> results = getResults(text,List.of(consoles),minPrice,MaxPrice);
        return toArrayList(results);
    }

    public ArrayList<JuegoStock> searchGames(String text) {
        return searchGames(text,Consolas.valuesAsList(),JuegoStock.MIN_PRICE,JuegoStock.MAX_PRICE);
    }

    public ArrayList<JuegoStock> searchGames(Consolas console) {
        ArrayList<Consolas> consoles = new ArrayList<>();
        consoles.add(console);
        return searchGames(null, consoles,JuegoStock.MIN_PRICE,JuegoStock.MAX_PRICE);

    }

    public ArrayList<JuegoStock> searchGames(float minPrice,float maxPrice) {
        return searchGames(null, Consolas.valuesAsList(),minPrice,maxPrice);

    }

    public ArrayList<JuegoStock> orderGames(OrderType orderType, OrderDir orderDir) {
        List<JuegoStock> results;
        switch (orderType) {
            case TITLE:
                results = getJuegos().stream().sorted(Comparator.comparing(JuegoStock::getTitle)).collect(Collectors.toList());
                break;
            case PRICE:
                results = getJuegos().stream().sorted(Comparator.comparing(JuegoStock::getPrice)).collect(Collectors.toList());
                break;
            case CONSOLE:
                results = getJuegos().stream().sorted(Comparator.comparing(JuegoStock::getConsole)).collect(Collectors.toList());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + orderType);
        }
        if (results.size() > 0)
            return new ArrayList<>(results);
        else
            return new ArrayList<>();
    }

    private List<JuegoStock> getResults(String title, List<Consolas> consoles, float minPrice, float maxPrice) {
        return getJuegos().stream().filter(i ->
                (title == null || i.getTitle().toUpperCase(Locale.ROOT).contains(title.toUpperCase(Locale.ROOT))) &&
                consoles.contains(i.getConsole()) &&
                (i.getPrice() >= minPrice && i.getPrice() <= maxPrice)

        ).collect(Collectors.toList());
    }

    private List<JuegoStock> getOrder(String title) {
        return getJuegos().stream().sorted(Comparator.comparing(JuegoStock::getTitle)).collect(Collectors.toList());
    }

    private ArrayList<JuegoStock> toArrayList(List<JuegoStock> juegoStocks) {
        return new ArrayList<>(juegoStocks);
    }

}
