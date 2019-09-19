package com.capgemini.molveno.reserveringssysteem.model;

/**
 * Enumerator for the room type
 */
public enum RoomType {
    SINGLE,
    DOUBLE,
    TWO_DOUBLE,
    PENTHOUSE;

    /**
     * Determines type of room
     * @param roomType {@link String String} containing data about the room type
     * @return {@link RoomType Type} of room
     */
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
