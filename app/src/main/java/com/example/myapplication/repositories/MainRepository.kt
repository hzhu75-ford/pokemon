package com.example.myapplication.repositories

import com.example.myapplication.model.data.Ability
import com.example.myapplication.model.data.Pokemon
import com.example.myapplication.model.data.SinglePokeDetails
import com.example.myapplication.model.remote.NetworkCallOutput

interface MainRepository {
    suspend fun getPokemon(): NetworkCallOutput<List<Pokemon>>
    suspend fun getSinglePokemon(id:String):NetworkCallOutput<SinglePokeDetails>
}
