package com.capgemini.molveno.reserveringssysteem.model;

public enum KamerType {
    SINGLE("single"),
    DOUBLE("double"),
    TWO_DOUBLE("2xdouble"),
    PENTHOUSE("penthouse");

    private String name;

    KamerType(String name) {
        this.name = name;
    }

    public static KamerType from(String kamerType) {
        kamerType = kamerType.toLowerCase().replace(" ", "");

        switch (kamerType) {
            case "single":
                return SINGLE;
            case "double":
                return DOUBLE;
            case "2xdouble":
                return TWO_DOUBLE;
            case "penthouse":
                return PENTHOUSE;
            default:
                return SINGLE;
        }
    }
}
