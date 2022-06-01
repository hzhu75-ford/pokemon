package com.example.myapplication.model.remote

import com.example.myapplication.model.data.Pokemon
import com.example.myapplication.model.data.PokemonResponse
import com.example.myapplication.model.data.SinglePokeDetails
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService  {
    @GET("pokemon?limit=100&offset=0")
    suspend fun getAllPokemon(): Response<PokemonResponse>

    @GET("pokemon/{pok_id}")
    fun getPokemonDetails(@Path("pok_id") id: String) : Response<SinglePokeDetails>

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2"

        var apiService:ApiService? = null
        fun getInstance(): ApiService {
            if( apiService == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                apiService = retrofit.create(ApiService::class.java)
            }
            return apiService!!

        }
    }
}
