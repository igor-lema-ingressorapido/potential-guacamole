package com.pokemon.Pokemon.service;

import com.pokemon.Pokemon.dao.PokemonDao;
import com.pokemon.Pokemon.model.Pokemon;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @author igorlema on 06/08/2017
 */
@Service
public class PokemonService {

   private final PokemonDao pokemonDao;

   public PokemonService(PokemonDao pokemonDao){
       this.pokemonDao = pokemonDao;
   }


   public void addPokemon(Pokemon pokemon){
      pokemonDao.addPokemon(pokemon);
   }

   public Pokemon getPokemon(long id){
      Pokemon rs = pokemonDao.findPokemonById(id);
      if(!rs.captured()){
         return null;
      }
      return rs;
   }

   public void deletePokemon(String pokemonName){
      pokemonDao.deletePokemon(pokemonDao.findPokemonIdByName(pokemonName));
   }

   public Boolean capturePokemon(String pokemonName){
      Random rand = new Random();
      if(rand.nextDouble() >= 0.5) {
         pokemonDao.capturePokemon(pokemonDao.findPokemonIdByName(pokemonName));
         return true;
      } else {
         return false;
      }
   }

   public void freePokemon(String pokemonName){
      pokemonDao.freePokemon(pokemonDao.findPokemonIdByName(pokemonName));
   }

   public List<Pokemon> listAllPokemon(){
       return pokemonDao.getAllPokemon();
   }

   public List<Pokemon> getAllPokemon(){
       return pokemonDao.getAllCapturedPokemon();
   }

}
