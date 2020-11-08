package com.example.pokemon.services;

import com.example.pokemon.entities.Pokemon;
import com.example.pokemon.mappers.PokemonMapper;
import com.example.pokemon.repository.PokemonRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PokemonService {
    @Autowired
    private  PokemonRepository pokemonRepository;
    @Autowired
    private  PokemonConsumerService pokemonConsumerService;
    @Autowired
    private  PokemonMapper pokemonMapper;

    @Cacheable(value = "pokemonCache", key = "#name")
    public List<Pokemon> findAll(String name) {
        System.out.println("Fresh data....");
        var pokemons = pokemonRepository.findAll();
            pokemons = pokemons.stream()
                    .filter(pokemon -> pokemon.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());

        if(pokemons.isEmpty()) {
            var pokemonDto = pokemonConsumerService.search(name);
            if(pokemonDto != null) {
                /*var movie = new Movie(moviesDto.getTitle(), moviesDto.getPlot(), moviesDto.getLanguage(),
                        moviesDto.getCountry(), moviesDto.getYear(), moviesDto.getImdbID(), List.of(moviesDto.getActors()));*/
                var pokemon = pokemonMapper.pokemonDtoToPokemon(pokemonDto);
                pokemons.add(this.save(pokemon));
            }
        }
        return pokemons;
    }

    public List<Pokemon> findByAbility(String ability) {
        var fetchedPokemons = pokemonRepository.findAll();
        ArrayList<Pokemon> pokemonArray= new ArrayList<Pokemon>();
        for (Pokemon p : fetchedPokemons) {
                if( p.getAbilities().toString().contains(ability)) {
                    pokemonArray.add(p);
                }
        }
        var pokemons = pokemonArray;

        if (pokemons.isEmpty()) {
            var pokemonDto = pokemonConsumerService.searchAbility(ability);

            if (pokemons != null) {

                for (var p : pokemonDto) {
                    var pokemon = pokemonMapper.pokemonDtoToPokemon(p);
                    pokemons.add(this.save(pokemon));
                }
            }
        }
        return pokemons;
    }
    public List<Pokemon> findByWeightAndAbility(int weight, String compare, String ability){
        List<Pokemon> pokemonsWithAbility = findByAbility(ability);
        ArrayList<Pokemon> pokemonsArray = new ArrayList<Pokemon>();

            if(compare.equals("equal")){
                for (Pokemon p : pokemonsWithAbility) {
                    if (p.getWeight() == weight) {
                        pokemonsArray.add(p);
                    }
                }
            }
            else if(compare.equals("min")){
                for (Pokemon p : pokemonsWithAbility) {
                    if (p.getWeight() >= weight) {
                        pokemonsArray.add(p);
                    }
                }
            }
            else if(compare.equals("max")){
                for (Pokemon p : pokemonsWithAbility) {
                if(p.getWeight()<=weight){
                    pokemonsArray.add(p);
                }
            }

        }
        var pokemons = pokemonsArray;

        return pokemons;
    }

        public Pokemon findById (String id){

            return pokemonRepository.findById(id).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the Pokemon"));
        }

        @CachePut(value = "pokemonCache", key = "#result.id")
        public Pokemon save (Pokemon pokemon){
            return pokemonRepository.save(pokemon);
        }

        @CachePut(value = "pokemonCache", key = "#id")
        public void update (String id, Pokemon pokemon){
            if (!pokemonRepository.existsById(id)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the Pokemon");
            }
            pokemon.setId(id);
            pokemonRepository.save(pokemon);
        }

        @CacheEvict(value = "pokemonCache", allEntries = true)
        public void delete (String id){
            if (!pokemonRepository.existsById(id)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the Pokemon");
            }
            pokemonRepository.deleteById(id);
        }
    }
