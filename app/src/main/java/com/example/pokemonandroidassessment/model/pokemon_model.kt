package com.example.pokemonandroidassessment.model

import com.google.gson.annotations.SerializedName

data class PokemonModel(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)
