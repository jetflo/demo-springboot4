package com.jetdev.demospringboot4.controllers;

import com.jetdev.demospringboot4.dto.PokemonDto;
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

    @GetMapping("/{name}")
    public PokemonDto getPokemon(@PathVariable String name) {
        return service.getPokemon(name);
    }
}