package org.example.models;

import java.util.ArrayList;

public class Tienda {
    private String name;
    private ArrayListGames games;

    public Tienda() {
    }

    public Tienda(String name, ArrayList<JuegoStock> games) {
        setName(name);
        setGames(games);
    }

    public Tienda(String name, ArrayListGames games) {
        setName(name);
        setJuegos(games);
    }

    public ArrayListGames getGames() {
        return games;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGames(ArrayList<JuegoStock> games) {
        this.games = new ArrayListGames(games);
    }

    public void setJuegos(ArrayListGames juegos) {
        this.games = new ArrayListGames(juegos);
    }


}
