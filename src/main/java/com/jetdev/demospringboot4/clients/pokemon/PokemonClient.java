package com.jetdev.demospringboot4.clients.pokemon;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url = "/pokemon")
public interface PokemonClient {

    @GetExchange("/{name}")
    PokemonApiResponse getPokemon(@PathVariable String name);
}