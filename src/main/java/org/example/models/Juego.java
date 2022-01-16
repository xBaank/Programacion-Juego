package org.example.models;

import org.example.exceptions.JuegoException;
import org.example.utils.JuegoUtils;

import java.util.Locale;

public class Juego {
    public static final float MAX_PRICE = 200;
    public static final float MIN_PRICE = 0;

    private String title;
    private Consolas console;
    private float price;

    public Juego() {

    }

    public Juego(String title, Consolas console, float price) {
        setTitle(title);
        setConsole(console);
        setPrice(price);
    }

    public Juego(String title, String console, float price) {
        setTitle(title);
        setConsole(Consolas.valueOfConsole(console.toUpperCase(Locale.ROOT)));
        setPrice(price);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().equals(""))
            throw new JuegoException("Title cannot be null or empty");
        this.title = title;
    }

    public Consolas getConsole() {
        return console;
    }

    public void setConsole(Consolas console) {
        this.console = console;
    }

    public void setConsole(String console) {
        Consolas consoleEnum = Consolas.valueOfConsole(console);
        this.console = consoleEnum;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        if (!JuegoUtils.isInRange(price, MIN_PRICE, MAX_PRICE))
            throw new JuegoException(String.format("Price must be between %.2f and %.2f", MIN_PRICE, MAX_PRICE));
        this.price = price;
    }
}
