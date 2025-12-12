package com.jetdev.demospringboot4.controllers;

import com.jetdev.demospringboot4.clients.pokemon.PokemonApiResponse;
import com.jetdev.demospringboot4.clients.pokemon.PokemonClient;
import com.jetdev.demospringboot4.clients.pokemon.PokemonSpeciesResponse;
import com.jetdev.demospringboot4.clients.pokemon.SpeciesClient;
import com.jetdev.demospringboot4.services.PokemonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class PokemonControllerMockMvcIT {

    @Autowired
    WebApplicationContext context;

    @MockitoBean
    PokemonClient pokemonClient;

    @MockitoBean
    SpeciesClient speciesClient;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void getPokemon_v1_0_shouldReturnSimplePokemonDto() throws Exception {
        // GIVEN
        var apiResponse = new PokemonApiResponse(25L, "pikachu", 4, 60);
        when(pokemonClient.getPokemon("pikachu")).thenReturn(apiResponse);

        // WHEN / THEN
        mockMvc.perform(
                       get("/api/pokemon/pikachu")
                               .accept(MediaType.APPLICATION_JSON)
                               .header("API-Version", "1.0")
               )
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(25))
               .andExpect(jsonPath("$.name").value("pikachu"))
               .andExpect(jsonPath("$.height").value(4))
               .andExpect(jsonPath("$.weight").value(60));
    }

    @Test
    void getPokemon_v1_2_shouldReturnPokemonWithSpeciesDto() throws Exception {
        // GIVEN
        var apiPokemon = new PokemonApiResponse(25L, "pikachu", 4, 60);
        var apiSpecies = new PokemonSpeciesResponse(190, 70);

        when(pokemonClient.getPokemon("pikachu")).thenReturn(apiPokemon);
        when(speciesClient.getSpecies("pikachu")).thenReturn(apiSpecies);

        // WHEN / THEN
        mockMvc.perform(
                       get("/api/pokemon/pikachu")
                               .accept(MediaType.APPLICATION_JSON)
                               .header("API-Version", "1.2")
               )
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(25))
               .andExpect(jsonPath("$.name").value("pikachu"))
               .andExpect(jsonPath("$.height").value(4))
               .andExpect(jsonPath("$.weight").value(60))
               .andExpect(jsonPath("$.captureRate").value(190))
               .andExpect(jsonPath("$.baseHappiness").value(70));
    }

    @Test
    void getPokemon_v1_4_shouldReturnFakeBulbi() throws Exception {
        mockMvc.perform(
                       get("/api/pokemon/whatever")
                               .accept(MediaType.APPLICATION_JSON)
                               .header("API-Version", "1.4")
               )
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1))
               .andExpect(jsonPath("$.name").value("bulbi"))
               .andExpect(jsonPath("$.height").value(1))
               .andExpect(jsonPath("$.weight").value(1));
    }

    @Test
    void unsupportedVersion_shouldReturn400() throws Exception {
        mockMvc.perform(
                       get("/api/pokemon/pikachu")
                               .accept(MediaType.APPLICATION_JSON)
                               .header("API-Version", "1.3")
               )
               .andExpect(status().isBadRequest());
    }
}