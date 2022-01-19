package org.example.utils;

import org.example.models.Consolas;
import org.example.models.Juego;
import org.example.models.JuegoStock;
import org.example.models.Tienda;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class JuegoUtils {
    /**
     * If the number is in the specified range (inclusive).
     * @param number number specified
     * @return true if it's in the range
     */
    public static boolean isInRange(float number,float from, float to) {
        return number >= from && number <= to;
    }

    public static Tienda generateShop(int gamesCount) {
        Tienda tienda = new Tienda();
        ArrayList<JuegoStock> juegoStocks = new ArrayList<>();
        for (int i = 0; i < gamesCount; i++) {
            Juego  juego = new Juego(getRandomTitle(),getRandomConsole(),getRandomPrice());
            juegoStocks.add(new JuegoStock(juego,getRandomInt(50)));
        }
        return new Tienda(getRandomTitle(),juegoStocks);
    }

    public static String getRandomTitle() {
        return UUID.randomUUID().toString();
    }

    public static Consolas getRandomConsole() {
        return Consolas.getRandomValue();
    }

    public static float getRandomPrice() {
        Random random = new Random();
        return random.nextInt((int) (Juego.MAX_PRICE-Juego.MIN_PRICE)) - Juego.MIN_PRICE;
    }

    public static int getRandomInt(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }


}
