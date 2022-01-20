package org.example.generator;

import org.example.enums.Consolas;
import org.example.models.Juego;
import org.example.models.JuegoStock;
import org.example.models.Tienda;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class TiendaGenerator {

    public static Tienda generateShop(int gamesCount) {
        Tienda tienda = new Tienda();
        ArrayList<JuegoStock> juegoStocks = new ArrayList<>();
        for (int i = 0; i < gamesCount; i++) {
            Juego juego = new Juego(getRandomTitle(), getRandomConsole(), getRandomPrice());
            juegoStocks.add(new JuegoStock(juego, getRandomInt(50)));
        }
        return new Tienda(getRandomTitle(), juegoStocks);
    }

    public static String getRandomTitle() {
        return UUID.randomUUID().toString();
    }

    public static Consolas getRandomConsole() {
        return Consolas.getRandomValue();
    }

    public static float getRandomPrice() {
        Random random = new Random();
        return random.nextInt((int) (Juego.MAX_PRICE - Juego.MIN_PRICE)) - Juego.MIN_PRICE;
    }

    public static int getRandomInt(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }


}
