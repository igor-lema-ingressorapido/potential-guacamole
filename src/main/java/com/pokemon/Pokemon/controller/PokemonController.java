package com.pokemon.Pokemon.controller;

import com.pokemon.Pokemon.model.Pokemon;
import com.pokemon.Pokemon.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author igorlema on 08/08/2017chec
 */
@RestController
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @RequestMapping("/")
    public @ResponseBody String greeting(){
        return "hellooo";
    }

    @RequestMapping(value = "/{pkmn-id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Pokemon getPokemon(@PathVariable("pkmn-id") long id){
        return pokemonService.getPokemon(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    @ResponseStatus(HttpStatus.OK)
    public void addPokemon(@RequestBody Pokemon pokemon) {
        pokemonService.addPokemon(pokemon);
    }

    @RequestMapping(value = "/{pkmn-name}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deletePokemon(@PathVariable("pkmn-name") String name) {
        pokemonService.deletePokemon(name);
    }

    @RequestMapping(value = "/{pkmn-name}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public String capturePokemon(@PathVariable("pkmn-name") String name) {
        if (pokemonService.capturePokemon(name)){
            return "Fail";
        }
        return "Success";
    }

    @RequestMapping(value = "/free/{pkmn-name}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void freePokemon(@PathVariable("pkmn-name") String name) {
        pokemonService.freePokemon(name);
    }

    @RequestMapping(value = "/pkmn", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Pokemon> listAllPokemon(){
        return pokemonService.listAllPokemon();
    }

    @RequestMapping(value = "/mypkmn", method = RequestMethod.GET)
    public List<Pokemon> getAllPokemon(){
        return pokemonService.getAllPokemon();
    }

    //TODO: Change Response Status to ResponseBody...
}
