package com.example.pokemon.services;

import com.example.pokemon.dto.AbilityDto;
import com.example.pokemon.dto.PokemonDto;
import com.example.pokemon.entities.Ability;
import com.example.pokemon.entities.Pokemon;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@ConfigurationProperties(value = "example.pokemon", ignoreUnknownFields = false)
public class PokemonConsumerService {
    private final RestTemplate restTemplate;
    private String url;

    public PokemonConsumerService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public PokemonDto search(String name) {
        var urlWithNameQuery = url + "pokemon/" + name;

        var pokemon = restTemplate.getForObject(urlWithNameQuery, PokemonDto.class);
        System.out.println("pokemon consumer service: " + pokemon.getName());

        if(pokemon == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pokemon found.");
        }
        System.out.println(pokemon);
        return pokemon;
    }

   public List<PokemonDto> searchAbility(String name) {

       var urlWithNameQuery = url + "ability/" + name ;

       var ability = restTemplate.getForObject(urlWithNameQuery, AbilityDto.class);
       List<PokemonDto> pokemons = new ArrayList<PokemonDto>();
       var urls = ability.getUrls();
       for (var u : urls) {
           var pokemon = restTemplate.getForObject(u, PokemonDto.class);
           pokemons.add(pokemon);
       }
       System.out.println(pokemons);
       return pokemons;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
