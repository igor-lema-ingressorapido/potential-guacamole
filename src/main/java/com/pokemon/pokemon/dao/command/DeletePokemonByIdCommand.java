package com.pokemon.pokemon.dao.command;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class DeletePokemonByIdCommand implements Consumer<Long> {

    private static final String QUERY = "DELETE " +
                                        "FROM pokemon_trainer " +
                                        "WHERE ID = :id";
    private NamedParameterJdbcTemplate jdbcTemplate;

    public DeletePokemonByIdCommand(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public void accept(Long id) {
        Map<String, Object> parameters = new HashMap();
        parameters.put("id", id);
        jdbcTemplate.update(QUERY, parameters);
    }
}
