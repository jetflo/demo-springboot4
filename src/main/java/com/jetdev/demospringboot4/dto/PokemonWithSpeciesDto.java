package com.jetdev.demospringboot4.dto;

public record PokemonWithSpeciesDto(
        Long id,
        String name,
        Integer height,
        Integer weight,
        Integer captureRate,
        Integer baseHappiness
) {}
