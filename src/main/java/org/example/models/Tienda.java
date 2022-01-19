package org.example.models;

import java.util.*;
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
        ArrayList<Consolas> consolas = new ArrayList<>();
        consolas.add(console);
        return searchGames(null,consolas,JuegoStock.MIN_PRICE,JuegoStock.MAX_PRICE);

    }

    public ArrayList<JuegoStock> searchGames(float minPrice,float maxPrice) {
        return searchGames(null, Consolas.valuesAsList(),minPrice,maxPrice);

    }

    public ArrayList<JuegoStock> orderGames() {
        List<JuegoStock> results;
        results = getJuegos().stream().sorted().collect(Collectors.toList());
        if(results.size() > 0)
            return new ArrayList<>(results);
        else
            return new ArrayList<>();
    }

    private List<JuegoStock> getResults(String title, List<Consolas> consoles, float minPrice, float maxPrice) {
        List<JuegoStock> results;
        return getJuegos().stream().filter(i ->
                (title == null || i.getTitle().toUpperCase(Locale.ROOT).contains(title.toUpperCase(Locale.ROOT))) &&
                consoles.contains(i.getConsole()) &&
                (i.getPrice() >= minPrice && i.getPrice() <= maxPrice)

        ).collect(Collectors.toList());
    }

    private List<JuegoStock> getOrder(String title) {
        List<JuegoStock> results;
        return getJuegos().stream().sorted(Comparator.comparing(JuegoStock::getTitle)).collect(Collectors.toList());
    }

    private ArrayList<JuegoStock> toArrayList(List<JuegoStock> juegoStocks) {
        return new ArrayList<>(juegoStocks);
    }

}
