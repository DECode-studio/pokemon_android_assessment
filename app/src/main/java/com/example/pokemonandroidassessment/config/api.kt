package com.example.pokemonandroidassessment.config

import com.example.pokemonandroidassessment.model.PokemonDetailModel
import com.example.pokemonandroidassessment.model.PokemonModel
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

open class ApiConfig {
    val baseUrl: String = "https://pokeapi.co/";
}

interface ListPokemonService {
    @GET("/api/v2/pokemon/")
    fun getListPokemon(): Call<JsonObject>
}

interface DetailPokemonService {
    @GET("/api/v2/pokemon/{pokemonId}")
    fun getPokemonById(@Path("pokemonId") id: Int): Call<PokemonDetailModel>
}