package com.helieu.androidtutorials.recyclerview.utilities;

public class StringUtility {

    /**
     * Checks if the string is null
     * @param string
     * @return isNull
     */
    public static boolean isNull(String string) {
        if (string == null || "null".equalsIgnoreCase(string) || string.isEmpty() || string.matches("\\s+")) {
            return true;
        } else {
            return false;
        }
    }
}