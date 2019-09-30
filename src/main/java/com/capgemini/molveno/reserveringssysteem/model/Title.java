package com.capgemini.molveno.reserveringssysteem.model;

public enum Title {

    MR("Mr."),
    MS("Ms. "),
    MRS("Mrs");

    private String title;

    private Title(String title) {
        this.title = title;
    }

    public String getAction() {
        return this.title;
    }
}
