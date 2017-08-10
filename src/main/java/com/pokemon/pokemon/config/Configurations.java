package com.pokemon.pokemon.config;

import com.pokemon.pokemon.dao.PokemonDao;
import com.pokemon.pokemon.dao.PokemonDaoImpl;
import com.pokemon.pokemon.service.PokemonService;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author igorlema on 05/08/16
 */
@Configuration
public class Configurations {

    @Autowired
    private Environment env;

    @Bean
    @Autowired
    public HikariDataSource dataSource() {
            HikariDataSource dataSource = new HikariDataSource();

            dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
            dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
            dataSource.setUsername(env.getProperty("jdbc.username"));
            dataSource.setPassword(env.getProperty("jdbc.password"));
            dataSource.setConnectionTestQuery(env.getProperty("jdbc.testQuery"));

            return dataSource;
    }

    @Bean
    @Autowired
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @Autowired
    public PokemonDao pokemonDao(JdbcTemplate jdbcTemplate) {
        return new PokemonDaoImpl(jdbcTemplate);
    }

    @Bean
    @Autowired
    public PokemonService pokemonService(PokemonDao pokemonDao){
        return new PokemonService(pokemonDao);
    }
}
