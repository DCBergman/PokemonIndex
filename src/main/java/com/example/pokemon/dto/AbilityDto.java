package com.example.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AbilityDto {

    @JsonProperty("name")
    private String name;
    private List<String> urls;


    @JsonProperty("pokemon")
    private Object[] abilityPokemons;


    public List<String> unpackNestedUrl(){
        List<String> urlStrings = new ArrayList<String>();
        String start = "url=";
        for(int i= 0; i<abilityPokemons.length; i++){
            int from = abilityPokemons[i].toString().indexOf(start) + start.length();
            int to = abilityPokemons[i].toString().lastIndexOf("},");
            String url = abilityPokemons[i].toString().substring(from, to);
            urlStrings.add(url);
        }
        return urlStrings;

    }

    public AbilityDto() {
    }

    public AbilityDto(String name, List<String> url) {
        this.name = name;
        this.urls = url;
    }

    public String getName() {

        return name;
    }

    public List<String> getUrls() {
        urls = unpackNestedUrl();
       // urls = testFlatMap();

        return urls;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrls(List<String> urls) {

        this.urls = urls;
    }
}

