package com.example.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PokemonDto {
    @JsonProperty("name")
    private String name;
    @JsonProperty("height")
    private int height;
    @JsonProperty("weight")
    private int weight;
    @JsonProperty("abilities")
    private List<Object> abilities;


    public PokemonDto() {
    }

    public PokemonDto(String name, int height, int weight, List<Object> abilities) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.abilities = abilities;
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
