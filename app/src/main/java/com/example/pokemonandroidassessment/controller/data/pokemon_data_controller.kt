package com.example.pokemonandroidassessment.controller.data

import com.example.pokemonandroidassessment.config.ApiConfig
import com.example.pokemonandroidassessment.config.DetailPokemonService
import com.example.pokemonandroidassessment.config.ListPokemonService
import com.example.pokemonandroidassessment.model.PokemonDetailModel
import com.example.pokemonandroidassessment.model.PokemonModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonDataController : ApiConfig() {
    private lateinit var listPokemonService: ListPokemonService
    private lateinit var detailPokemonService: DetailPokemonService

    fun getListPokemon() : List<PokemonModel>? {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        listPokemonService = retrofit.create(ListPokemonService::class.java)
        val result = listPokemonService.getListPokemon().execute().body()
        val resultsArray = result?.getAsJsonArray("results")
        var data = ArrayList<PokemonModel>();

        resultsArray?.forEach { result ->
            val name = result.asJsonObject.get("name").asString
            val url = result.asJsonObject.get("url").asString
            val pokemon = PokemonModel(name, url)
            data.add(pokemon)
        }

        return data;
    }

    fun getDetailPokemon(id: Int) : PokemonDetailModel? {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        detailPokemonService = retrofit.create(DetailPokemonService::class.java)
        val data = detailPokemonService.getPokemonById(id).execute().body()
        return data
    }
}