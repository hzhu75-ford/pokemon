package com.example.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemPokemonsBinding
import com.example.myapplication.model.data.Pokemon


class PokemonListAdapter : RecyclerView.Adapter<PokemonViewHolder>()
 {
     var pokemon : List<Pokemon> = listOf()

     fun setDataList(pokemon: List<Pokemon>){
         this.pokemon = pokemon
         notifyDataSetChanged()
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = ItemPokemonsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemon[position])
    }

     override fun getItemCount(): Int {
         return pokemon.size
     }
 }


