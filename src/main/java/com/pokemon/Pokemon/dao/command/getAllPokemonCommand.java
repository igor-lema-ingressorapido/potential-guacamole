package com.pokemon.Pokemon.dao.command;

import com.pokemon.Pokemon.model.ImmutablePokemon;
import com.pokemon.Pokemon.model.Pokemon;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.function.Supplier;

public class getAllPokemonCommand implements Supplier<List<Pokemon>> {

    private static final String QUERY = "SELECT * " +
                                        "FROM pokemon_trainer";
    private NamedParameterJdbcTemplate jdbcTemplate;

    public getAllPokemonCommand(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public List<Pokemon> get() {
        try {
            return jdbcTemplate.query(QUERY,
                (rs, i) -> ImmutablePokemon.builder()
                    .name(rs.getString("name"))
                    .description(rs.getString("info"))
                    .captured(rs.getBoolean("captured"))
                    .build());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
