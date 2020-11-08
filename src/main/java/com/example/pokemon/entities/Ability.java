package com.example.pokemon.entities;

import java.util.List;

public class Ability {

    private String name;
    private List<String> urls;

    public Ability(String name, List<String> urls) {
        this.name = name;
        this.urls = urls;
    }

    public Ability() {
    }

    public String getName() {
        return name;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
