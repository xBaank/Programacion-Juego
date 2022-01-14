package org.example.models;

import org.example.exceptions.JuegoException;
import org.example.utils.JuegoUtils;

import java.util.Locale;

public class Juego {
    public static final float MAXPRICE = 200;
    public static final float MINPRICE = 0;

    private String title;
    private Consolas console;
    private float price;

    public Juego(String title, Consolas console, float price) {
        setTitle(title);
        setConsole(console);
        setPrice(price);
    }
    public Juego(String title, String console, float price) {
        setTitle(title);
        setConsole(Consolas.valueOf(console.toUpperCase(Locale.ROOT)));
        setPrice(price);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Consolas getConsole() {
        return console;
    }

    public void setConsole(Consolas console) {
        this.console = console;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        if(!JuegoUtils.isInRange(price,MINPRICE,MAXPRICE))
            throw new JuegoException(String.format("Price must be between %2g and %2g",MINPRICE,MAXPRICE));
        this.price = price;
    }
}
