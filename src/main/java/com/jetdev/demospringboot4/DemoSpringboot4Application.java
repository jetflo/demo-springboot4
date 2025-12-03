package com.jetdev.demospringboot4;

import com.jetdev.demospringboot4.clients.pokemon.PokemonClient;
import com.jetdev.demospringboot4.clients.pokemon.SpeciesClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.service.registry.ImportHttpServices;

@SpringBootApplication
@ImportHttpServices(group = "pokemon", types = {PokemonClient.class, SpeciesClient.class})
//@ImportHttpServices(group = "pokemon", basePackages = "com.jetdev.demospringboot4.clients.pokemon")
public class DemoSpringboot4Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringboot4Application.class, args);
    }

}
