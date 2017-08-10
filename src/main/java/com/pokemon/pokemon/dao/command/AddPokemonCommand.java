package com.pokemon.pokemon.dao.command;

import com.pokemon.pokemon.model.Pokemon;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;


public class AddPokemonCommand implements Consumer<Pokemon> {

   private final NamedParameterJdbcTemplate jdbcTemplate;
   private String INSERT = "INSERT INTO pokemon_trainer " +
                           "  ( " +
                           "    name," +
                           "    info," +
                           "    captured" +
                           "  )" +
                           "  VALUES (:name,:info,:captured)";

   public AddPokemonCommand(JdbcTemplate jdbcTemplate) {
       this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
   }

    @Override
    public void accept(Pokemon pokemon) {
        Map<String, Object> parameters = new HashMap();
        parameters.put("name", pokemon.name());
        parameters.put("info", pokemon.description());
        parameters.put("captured", pokemon.captured());
        jdbcTemplate.update(INSERT, parameters);
    }
}
