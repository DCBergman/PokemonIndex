package com.example.pokemon.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serializable;
import java.util.List;

public class Pokemon implements Serializable {
    private static final long serialVersionUID = 2872294642655459903L;
    @Id
    private String id;
    private String name;
    private int height;
    private int weight;
    private List<Object> abilities;

    public Pokemon() { }

    public Pokemon(String name, int height, int weight, List<Object> abilities) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.abilities = abilities;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public List<Object> getAbilities() {
        return abilities;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setAbilities(List<Object> abilities) {
        this.abilities = abilities;
    }
}
