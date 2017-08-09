package com.pokemon.Pokemon.dao.command;

import com.pokemon.Pokemon.model.ImmutablePokemon;
import com.pokemon.Pokemon.model.Pokemon;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class findPokemonIdByNameCommand implements Function<String, Long> {

    private static final String QUERY = "SELECT id " +
                                        "FROM pokemon_trainer " +
                                        "WHERE name = :name";
    private NamedParameterJdbcTemplate jdbcTemplate;

    public findPokemonIdByNameCommand(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public Long apply(String name) {
        Map<String, Object> parameters = new HashMap();
        parameters.put("name", name);
        try {
            return jdbcTemplate.queryForObject(QUERY, parameters,
                (rs, i) -> rs.getLong("id"));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
