package com.pokemon.Pokemon.dao.command;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class freePokemonCommand implements Consumer<Long> {

    private static final String QUERY = "UPDATE pokemon_trainer " +
                                        "SET captured = false " +
                                        "WHERE ID = :id";
    private NamedParameterJdbcTemplate jdbcTemplate;

    public freePokemonCommand(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public void accept(Long id) {
        Map<String, Object> parameters = new HashMap();
        parameters.put("id", id);
        jdbcTemplate.update(QUERY, parameters);
    }
}
