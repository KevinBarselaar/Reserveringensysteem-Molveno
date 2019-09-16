package com.capgemini.molveno.reserveringssysteem.model;

public enum RoomType {
    SINGLE("single"),
    DOUBLE("double"),
    TWO_DOUBLE("2xdouble"),
    PENTHOUSE("penthouse");

    private String name;

    RoomType(String name) {
        this.name = name;
    }

    public static RoomType from(String roomType) {
        roomType = roomType.toLowerCase().replace(" ", "");

        switch (roomType) {
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
