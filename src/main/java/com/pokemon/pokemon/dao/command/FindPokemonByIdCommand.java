package com.pokemon.pokemon.dao.command;

import com.pokemon.pokemon.model.ImmutablePokemon;
import com.pokemon.pokemon.model.Pokemon;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class FindPokemonByIdCommand implements Function<Long, Pokemon> {

    private static final String QUERY = "SELECT * " +
                                        "FROM pokemon_trainer " +
                                        "WHERE ID = :id";
    private NamedParameterJdbcTemplate jdbcTemplate;

    public FindPokemonByIdCommand(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public Pokemon apply(Long id) {
        Map<String, Object> parameters = new HashMap();
        parameters.put("id", id);
        try {
            return jdbcTemplate.queryForObject(QUERY, parameters,
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
