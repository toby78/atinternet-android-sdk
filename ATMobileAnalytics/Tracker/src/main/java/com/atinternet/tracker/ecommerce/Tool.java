package com.atinternet.tracker.ecommerce;

class Tool {

    static double parseDoubleFromString(String s) {
        try {
            return Double.parseDouble(s);
        } catch (Exception ignored) {
            return 0.0;
        }
    }

    static int parseIntFromString(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception ignored) {
            return 0;
        }
    }
}
