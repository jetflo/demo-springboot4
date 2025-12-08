package com.jetdev.demospringboot4.services;

import com.jetdev.demospringboot4.clients.pokemon.PokemonClient;
import com.jetdev.demospringboot4.clients.pokemon.SpeciesClient;
import com.jetdev.demospringboot4.dto.PokemonDto;
import com.jetdev.demospringboot4.dto.SpeciesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PokemonService {

    private final PokemonClient pokemonClient;
    private final SpeciesClient speciesClient;

    public PokemonDto getPokemon(String name) {

        var pokemon = pokemonClient.getPokemon(name);

        return new PokemonDto(
                pokemon.id(),
                pokemon.name(),
                pokemon.height(),
                pokemon.weight()
        );
    }

    public SpeciesDto getPokemonSpecies(String name) {
        var species = speciesClient.getSpecies(name);

        return new SpeciesDto(
                species.capture_rate(),
                species.base_happiness()
        );
    }
}