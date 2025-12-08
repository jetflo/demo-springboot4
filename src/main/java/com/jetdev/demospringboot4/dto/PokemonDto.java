package com.jetdev.demospringboot4.dto;

public record PokemonDto(
        Long id,
        String name,
        Integer height,
        Integer weight
) {}