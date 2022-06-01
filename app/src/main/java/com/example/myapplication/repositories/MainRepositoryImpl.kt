package com.example.myapplication.repositories

import com.example.myapplication.model.data.Pokemon
import com.example.myapplication.model.data.SinglePokeDetails
import com.example.myapplication.model.remote.ApiService
import com.example.myapplication.model.remote.NetworkCallOutput

class MainRepositoryImpl(private val api: ApiService) : MainRepository {

    override suspend fun getPokemon(): NetworkCallOutput<List<Pokemon>> {
        val response = api.getAllPokemon()
        return if (response.isSuccessful ) {
            response.body()?.results?.let { res ->
                NetworkCallOutput.Success(res)
            } ?: NetworkCallOutput.Error("Empty response")
        } else {
            NetworkCallOutput.Error(response.message())
        }
    }

    override suspend fun getSinglePokemon(id:String): NetworkCallOutput<SinglePokeDetails> {
        val response = api.getPokemonDetails(id)
        return if (response.isSuccessful ) {
            response.body().let { res->
                NetworkCallOutput.Success(res!!)
            } ?: NetworkCallOutput.Error("Empty response")
        } else {
            NetworkCallOutput.Error(response.message())
        }
    }
}

