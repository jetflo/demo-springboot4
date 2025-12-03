package com.jetdev.demospringboot4.clients.pokemon;

public record PokemonSpeciesResponse(
        Boolean isLegendary,
        Boolean isMythical
) {}
