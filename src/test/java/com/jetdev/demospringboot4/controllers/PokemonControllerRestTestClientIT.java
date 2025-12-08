package com.jetdev.demospringboot4.controllers;

import com.jetdev.demospringboot4.clients.pokemon.PokemonApiResponse;
import com.jetdev.demospringboot4.clients.pokemon.PokemonClient;
import com.jetdev.demospringboot4.clients.pokemon.PokemonSpeciesResponse;
import com.jetdev.demospringboot4.clients.pokemon.SpeciesClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
class PokemonControllerRestTestClientIT {

    @Autowired
    RestTestClient restTestClient;

    @MockitoBean
    PokemonClient pokemonClient;

    @MockitoBean
    SpeciesClient speciesClient;

    @Test
    void getPokemon_v1_0_shouldReturnSimplePokemonDto() {
        // GIVEN
        var apiResponse = new PokemonApiResponse(25L, "pikachu", 4, 60);
        when(pokemonClient.getPokemon("pikachu")).thenReturn(apiResponse);

        // WHEN / THEN
        restTestClient
                .get()
                .uri("/api/pokemon/pikachu")
                .accept(MediaType.APPLICATION_JSON)
                .header("API-Version", "1.0")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(25)
                .jsonPath("$.name").isEqualTo("pikachu")
                .jsonPath("$.height").isEqualTo(4)
                .jsonPath("$.weight").isEqualTo(60);
    }

    @Test
    void getPokemon_v1_2_shouldReturnPokemonWithSpeciesDto() {
        // GIVEN
        var apiPokemon = new PokemonApiResponse(25L, "pikachu", 4, 60);
        var apiSpecies = new PokemonSpeciesResponse(190, 70);

        when(pokemonClient.getPokemon("pikachu")).thenReturn(apiPokemon);
        when(speciesClient.getSpecies("pikachu")).thenReturn(apiSpecies);

        // WHEN / THEN
        restTestClient
                .get()
                .uri("/api/pokemon/pikachu")
                .accept(MediaType.APPLICATION_JSON)
                .header("API-Version", "1.2")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(25)
                .jsonPath("$.name").isEqualTo("pikachu")
                .jsonPath("$.height").isEqualTo(4)
                .jsonPath("$.weight").isEqualTo(60)
                .jsonPath("$.captureRate").isEqualTo(190)
                .jsonPath("$.baseHappiness").isEqualTo(70);
    }

    @Test
    void getPokemon_v1_4_shouldReturnFakeBulbi() {
        restTestClient
                .get()
                .uri("/api/pokemon/{name}", "whatever")
                .accept(MediaType.APPLICATION_JSON)
                .header("API-Version", "1.4")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("bulbi")
                .jsonPath("$.height").isEqualTo(1)
                .jsonPath("$.weight").isEqualTo(1);
    }

    @Test
    void unsupportedVersion_shouldReturn400() {
        restTestClient
                .get()
                .uri("/api/pokemon/{name}", "pikachu")
                .accept(MediaType.APPLICATION_JSON)
                .header("API-Version", "1.3")
                .exchange()
                .expectStatus().isBadRequest();
    }
}