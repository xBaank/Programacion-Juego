package org.example.utils;

public class JuegoUtils {
    /**
     * If the number is in the specified range (inclusive).
     * @param number number specified
     * @return true if it's in the range
     */
    public static boolean isInRange(float number,float from, float to) {
        return number >= from && number <= to;
    }
}
