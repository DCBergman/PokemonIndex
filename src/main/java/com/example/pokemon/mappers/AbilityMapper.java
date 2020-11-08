package com.example.pokemon.mappers;


import com.example.pokemon.dto.AbilityDto;
import com.example.pokemon.entities.Ability;
import org.mapstruct.Mapper;

@Mapper(uses = {StringToListMapper.class})
public interface AbilityMapper {
    Ability abilityDtoToAbility(AbilityDto pokemonDto);
    AbilityDto abilityToAbilityDto(Ability ability);
}
