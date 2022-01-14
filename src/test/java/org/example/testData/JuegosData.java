package org.example.testData;

import org.example.models.Juego;

import java.lang.reflect.Executable;

public class JuegosData {
    public static final Runnable RANGE_IN_LIMIT_DOWN  = () -> new Juego("Fortnite","PC",0);
    public static final Runnable RANGE_IN_LIMIT_UP  = () -> new Juego("Fortnite","PC",200);
    public static final Runnable RANGE_UNDER_LIMIT  = () -> new Juego("Fortnite","PC",-0.01F);
    public static final Runnable RANGE_OVER_LIMIT  = () -> new Juego("Fortnite","PC",200.01F);
}
