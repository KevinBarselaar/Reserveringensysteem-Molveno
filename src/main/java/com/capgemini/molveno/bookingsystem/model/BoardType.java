package com.capgemini.molveno.bookingsystem.model;

/**
 * Enumeration containing the different types of boarding accommodations offered.
 */
public enum BoardType {

    BED_AND_BREAKFAST("Bed and Breakfast"),
    HALF_BOARD("Half board"),
    ACCOMMODATIONS("Accommodations");

    private String boardType;

    public String getBoardType() {
        return boardType;
    }

    BoardType(String boardType) {
        this.boardType = boardType;
    }
}
