package com.capgemini.molveno.bookingsystem.model;

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
