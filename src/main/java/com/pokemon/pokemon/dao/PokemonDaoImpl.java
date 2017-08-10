package com.pokemon.pokemon.dao;

import com.pokemon.pokemon.dao.command.AddPokemonCommand;
import com.pokemon.pokemon.dao.command.CapturePokemonCommand;
import com.pokemon.pokemon.dao.command.DeletePokemonByIdCommand;
import com.pokemon.pokemon.dao.command.FindPokemonByIdCommand;
import com.pokemon.pokemon.dao.command.FindPokemonIdByNameCommand;
import com.pokemon.pokemon.dao.command.FreePokemonCommand;
import com.pokemon.pokemon.dao.command.GetAllCapturedPokemonCommand;
import com.pokemon.pokemon.dao.command.GetAllPokemonCommand;
import com.pokemon.pokemon.model.Pokemon;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PokemonDaoImpl implements PokemonDao {

    private JdbcTemplate jdbcTemplate;

    public PokemonDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addPokemon(Pokemon pokemon) {
        new AddPokemonCommand(jdbcTemplate).accept(pokemon);
    }
    
    @Override
    public void capturePokemon(Long pokemonId) {
        new CapturePokemonCommand(jdbcTemplate).accept(pokemonId);
    }

    @Override
    public void freePokemon(Long pokemonId) {
        new FreePokemonCommand(jdbcTemplate).accept(pokemonId);
    }

    @Override
    public void deletePokemon(Long pokemonId) {
        new DeletePokemonByIdCommand(jdbcTemplate).accept(pokemonId);
    }

    @Override
    public Long findPokemonIdByName(String pokemonName) {
        return new FindPokemonIdByNameCommand(jdbcTemplate).apply(pokemonName);
    }

    @Override
    public Pokemon findPokemonById(Long pokemonId) {
        return new FindPokemonByIdCommand(jdbcTemplate).apply(pokemonId);
    }

    @Override
    public List<Pokemon> getAllPokemon() {
        return new GetAllPokemonCommand(jdbcTemplate).get();
    }

    @Override
    public List<Pokemon> getAllCapturedPokemon() {
        return new GetAllCapturedPokemonCommand(jdbcTemplate).get();
    }
}
