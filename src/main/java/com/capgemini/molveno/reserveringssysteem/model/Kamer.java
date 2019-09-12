package com.capgemini.molveno.reserveringssysteem.model;

import javax.persistence.*;

@Entity
public class Kamer {

    @Id
    private int id;
    private KamerType type;
    private int volwassenenCapaciteit;
    private int kinderenCapaciteit;

    @OrderColumn
    @ElementCollection(targetClass = BedType.class)
    @Enumerated(EnumType.STRING)
    private BedType[] bedTypes;

    private boolean invalideVriendelijk;
    private int verdieping;

    public Kamer(int id, KamerType type, int volwassenenCapaciteit, int kinderenCapaciteit, boolean invalideVriendelijk, int verdieping, BedType... bedTypes) {
        this.id = id;
        this.type = type;
        this.volwassenenCapaciteit = volwassenenCapaciteit;
        this.kinderenCapaciteit = kinderenCapaciteit;
        this.bedTypes = bedTypes;
        this.invalideVriendelijk = invalideVriendelijk;
        this.verdieping = verdieping;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public KamerType getType() {
        return type;
    }

    public void setType(KamerType type) {
        this.type = type;
    }

    public int getVolwassenenCapaciteit() {
        return volwassenenCapaciteit;
    }

    public void setVolwassenenCapaciteit(int volwassenenCapaciteit) {
        this.volwassenenCapaciteit = volwassenenCapaciteit;
    }

    public int getKinderenCapaciteit() {
        return kinderenCapaciteit;
    }

    public void setKinderenCapaciteit(int kinderenCapaciteit) {
        this.kinderenCapaciteit = kinderenCapaciteit;
    }

    public BedType[] getBedTypes() {
        return bedTypes;
    }

    public void setBedTypes(BedType[] bedTypes) {
        this.bedTypes = bedTypes;
    }

    public boolean isInvalideVriendelijk() {
        return invalideVriendelijk;
    }

    public void setInvalideVriendelijk(boolean invalideVriendelijk) {
        this.invalideVriendelijk = invalideVriendelijk;
    }

    public int getVerdieping() {
        return verdieping;
    }

    public void setVerdieping(int verdieping) {
        this.verdieping = verdieping;
    }
}
