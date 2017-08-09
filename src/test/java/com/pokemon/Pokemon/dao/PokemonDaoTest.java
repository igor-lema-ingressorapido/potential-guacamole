package com.pokemon.Pokemon.dao;

import com.pokemon.Pokemon.config.DaosConfiguration;
import com.pokemon.Pokemon.model.ImmutablePokemon;
import com.pokemon.Pokemon.model.Pokemon;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= DaosConfiguration.class)
@TestPropertySource(locations="classpath:application.properties")
public class PokemonDaoTest {

    @Autowired
    private PokemonDao pokemonDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Pokemon mockPokemon;

    @Before
    public void setUp(){
        this.mockPokemon = ImmutablePokemon.builder()
            .name(RandomStringUtils.random(10))
            .description(RandomStringUtils.random(10))
            .captured(Math.random() < 0.5)
            .build();
    }

    @Test
    public void insert_withAllAttributes_shouldInsertSuccessfully() {
        pokemonDao.addPokemon(mockPokemon);
        Pokemon resultGet = pokemonDao.findPokemonById(pokemonDao.findPokemonIdByName(mockPokemon.name()));

        Pokemon expected = ImmutablePokemon.builder()
            .name(mockPokemon.name())
            .description(mockPokemon.description())
            .captured(mockPokemon.captured())
            .build();

        assertThat(expected).isEqualTo(mockPokemon);
        assertThat(resultGet).isEqualTo(expected);

    }

    @After
    public void cleanUp(){
        jdbcTemplate.update("Delete from pokemon_trainer where name = ?", mockPokemon.name());
    }

}
