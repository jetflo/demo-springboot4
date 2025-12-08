package com.jetdev.demospringboot4.controllers;

import com.jetdev.demospringboot4.dto.PokemonDto;
import com.jetdev.demospringboot4.dto.PokemonWithSpeciesDto;
import com.jetdev.demospringboot4.dto.SpeciesDto;
import com.jetdev.demospringboot4.services.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {

    private final PokemonService service;

    @GetMapping(value = "/{name}", version = "1.0")
    public PokemonDto getPokemon(@PathVariable String name) {
        return service.getPokemon(name);
    }

    @GetMapping(value = "/{name}", version = "1.2")
    public PokemonWithSpeciesDto getPokemonWithSpecies(@PathVariable String name) {
        final PokemonDto pokemon = service.getPokemon(name);
        final SpeciesDto pokemonSpecies = service.getPokemonSpecies(name);
        return new PokemonWithSpeciesDto(
                pokemon.id(),
                pokemon.name(),
                pokemon.height(),
                pokemon.weight(),
                pokemonSpecies.captureRate(),
                pokemonSpecies.baseHappiness()
        );
    }

    @GetMapping(value = "/{name}", version = "1.4")
    public PokemonDto falseResponse(@PathVariable String name) {
        return new PokemonDto(1L, "bulbi", 1, 1);
    }
}