package com.pokemon.pokemon.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutablePokemon.class)
@JsonSerialize(as = ImmutablePokemon.class)
public interface Pokemon {

    String name();

    String description();

    Boolean captured();
}
