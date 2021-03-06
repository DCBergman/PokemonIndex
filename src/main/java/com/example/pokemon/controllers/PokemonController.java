package com.example.pokemon.controllers;


import com.example.pokemon.entities.Pokemon;
import com.example.pokemon.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pokemons")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<List<Pokemon>> findPokemons(@RequestParam String name) {
        var pokemon = pokemonService.findAll(name);
        return ResponseEntity.ok(pokemon);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> findPokemonById(@PathVariable String id) {
        return ResponseEntity.ok(pokemonService.findById(id));
    }

    @GetMapping("/ability")
    public ResponseEntity<List<Pokemon>> findPokemonByAbility(@RequestParam String ability){
        var pokemons = pokemonService.findByAbility(ability);
        return ResponseEntity.ok(pokemons);
    }
    @GetMapping("/weight-and-ability")
    public ResponseEntity<List<Pokemon>> findPokemonByWeightAndAbility(@RequestParam int weight, @RequestParam String compare, @RequestParam String ability){
        var pokemons = pokemonService.findByWeightAndAbility(weight, compare, ability);
        return ResponseEntity.ok(pokemons);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Pokemon> savePokemon(@RequestBody Pokemon pokemon) {
        var savedPokemon = pokemonService.save(pokemon);
        var uri = URI.create("/api/v1/pokemons/" + savedPokemon.getId()); // /api/v1/pokemons/1
        return ResponseEntity.created(uri).body(savedPokemon);
    }

    @PutMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePokemon(@PathVariable String id, @RequestBody Pokemon pokemon) {
        pokemonService.update(id, pokemon);
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_USER"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePokemon(@PathVariable String id) {
        pokemonService.delete(id);
    }
}
