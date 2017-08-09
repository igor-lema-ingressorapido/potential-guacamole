package com.pokemon.Pokemon.dao;

import com.pokemon.Pokemon.model.Pokemon;

import java.util.List;

public interface PokemonDao {

    void addPokemon(Pokemon pokemon);

    void capturePokemon(Long pokemonId);

    void freePokemon(Long pokemonId);

    void deletePokemon(Long pokemonId);

    Long findPokemonIdByName(String pokemonName);

    Pokemon findPokemonById(Long pokemonId);

    List<Pokemon> getAllPokemon();

    List<Pokemon> getAllCapturedPokemon();

}
