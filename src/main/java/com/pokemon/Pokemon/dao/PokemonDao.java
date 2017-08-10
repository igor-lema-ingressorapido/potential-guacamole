package com.pokemon.Pokemon.dao;

import com.pokemon.Pokemon.model.Pokemon;

import java.util.List;

/**
 * @author igorlema on 06/08/2017
 */
public interface PokemonDao {

    /**
     * Add pokemon
     *
     * @param pokemon the pokemon object
     */
    void addPokemon(Pokemon pokemon);

    /**
     * Capture pokemon
     *
     * @param pokemonId the pokemonId
     */
    void capturePokemon(Long pokemonId);

    /**
     * Free pokemon
     *
     * @param pokemonId the pokemonId
     */
    void freePokemon(Long pokemonId);

    /**
     * Delete pokemon from database
     *
     * @param pokemonId the pokemonId
     */
    void deletePokemon(Long pokemonId);

    /**
     * Find pokemonId by pokemonName
     *
     * @param pokemonName the Name
     * @return pokemonId the Id
     */
    Long findPokemonIdByName(String pokemonName);

    /**
     * Find pokemon by pokemonId
     *
     * @param pokemonId
     * @return the pokemon object
     */
    Pokemon findPokemonById(Long pokemonId);

    /**
     * Returns all pokemon from database
     *
     * @return list of pokemons object
     */
    List<Pokemon> getAllPokemon();

    /**
     * Find all pokemon which are captured and return a list of it
     *
     * @return list of pokemons object
     */
    List<Pokemon> getAllCapturedPokemon();

}
