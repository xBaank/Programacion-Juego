package org.example.models;

import org.example.exceptions.JuegoException;

import java.util.Locale;

public enum Consolas {
    PC,
    XBOX,
    PLAY,
    SWITCH;

    /**
     * Gets the Enum value (case insensitive)
     *
     * @param value value we want to get
     * @return Enum value
     * @throws JuegoException
     */
    public static Consolas valueOfConsole(String value) throws JuegoException {
        Consolas result = null;


        if (value == null || value.trim().equals(""))
            throw new JuegoException("Value cannot be empty or null");

        try {
            for (Consolas constant : values()) {
                if (value.toUpperCase(Locale.ROOT).equals(constant.name().toUpperCase(Locale.ROOT))) {
                    result = constant;
                    break;
                }
            }
            if (result == null)
                throw new Exception();
        } catch (Exception ex) {
            throw new JuegoException("Value is not defined in the enum");
        }
        return result;
    }
}
