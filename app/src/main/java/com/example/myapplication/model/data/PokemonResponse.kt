package com.example.myapplication.model.data

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    @SerializedName("results")
    val results: List<Pokemon>
)