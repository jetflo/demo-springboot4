package com.jetdev.demospringboot4.clients.pokemon;

public record PokemonApiResponse(
        Long id,
        String name,
        Integer height,
        Integer weight
) {}