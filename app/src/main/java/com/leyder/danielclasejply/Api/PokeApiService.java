package com.leyder.danielclasejply.Api;

import com.leyder.danielclasejply.POKEMONRESPUESTA;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeApiService {
    @GET("pokemon")
    Call<POKEMONRESPUESTA> obtenerlistapokemon();



}
