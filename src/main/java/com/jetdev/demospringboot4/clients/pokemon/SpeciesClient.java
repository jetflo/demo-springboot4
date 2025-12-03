package com.jetdev.demospringboot4.clients.pokemon;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url = "/pokemon-species")
public interface SpeciesClient {

    @GetExchange("/{name}")
    PokemonSpeciesResponse getSpecies(@PathVariable String name);
}
