package com.pokemon.Pokemon.dao;

import com.pokemon.Pokemon.dao.command.addPokemonCommand;
import com.pokemon.Pokemon.dao.command.capturePokemonCommand;
import com.pokemon.Pokemon.dao.command.deletePokemonByIdCommand;
import com.pokemon.Pokemon.dao.command.findPokemonByIdCommand;
import com.pokemon.Pokemon.dao.command.findPokemonIdByNameCommand;
import com.pokemon.Pokemon.dao.command.freePokemonCommand;
import com.pokemon.Pokemon.dao.command.getAllCapturedPokemonCommand;
import com.pokemon.Pokemon.dao.command.getAllPokemonCommand;
import com.pokemon.Pokemon.model.Pokemon;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PokemonDaoImpl implements PokemonDao {

    private JdbcTemplate jdbcTemplate;

    public PokemonDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addPokemon(Pokemon pokemon) {
        new addPokemonCommand(jdbcTemplate).accept(pokemon);
    }
    
    @Override
    public void capturePokemon(Long pokemonId) {
        new capturePokemonCommand(jdbcTemplate).accept(pokemonId);
    }

    @Override
    public void freePokemon(Long pokemonId) {
        new freePokemonCommand(jdbcTemplate).accept(pokemonId);
    }

    @Override
    public void deletePokemon(Long pokemonId) {
        new deletePokemonByIdCommand(jdbcTemplate).accept(pokemonId);
    }

    @Override
    public Long findPokemonIdByName(String pokemonName) {
        return new findPokemonIdByNameCommand(jdbcTemplate).apply(pokemonName);
    }

    @Override
    public Pokemon findPokemonById(Long pokemonId) {
        return new findPokemonByIdCommand(jdbcTemplate).apply(pokemonId);
    }

    @Override
    public List<Pokemon> getAllPokemon() {
        return new getAllPokemonCommand(jdbcTemplate).get();
    }

    @Override
    public List<Pokemon> getAllCapturedPokemon() {
        return new getAllCapturedPokemonCommand(jdbcTemplate).get();
    }
}
