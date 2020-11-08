package com.example.pokemon.mappers;


import com.example.pokemon.dto.PokemonDto;
import com.example.pokemon.entities.Pokemon;
import org.mapstruct.Mapper;

@Mapper(uses = {StringToListMapper.class})
public interface PokemonMapper {
    Pokemon pokemonDtoToPokemon(PokemonDto pokemonDto);
    PokemonDto pokemonToPokemonDto(Pokemon pokemon);
}
