package com.vaadin.tutorial.crm.backend.entity;

public class VideoGameName {

    String name;
    String shortName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }


    @Override
    public String toString() {
        return "TitleUpperCase: " + this.name + " TitleShort: "+  this.shortName;
    }
}
