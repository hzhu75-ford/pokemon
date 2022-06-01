package com.example.myapplication.adapters

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemPokemonsBinding
import com.example.myapplication.model.data.Pokemon
import com.example.myapplication.ui.MainActivity
import com.example.myapplication.ui.fragment.PokemonListFragment

class PokemonViewHolder (
    private val binding: ItemPokemonsBinding
) : RecyclerView.ViewHolder(binding.root){

    private val context by lazy{MainActivity()}


    fun bind(pokemon: Pokemon) {
        binding.apply {

            textViewPokemonName.text = pokemon.name.capitalize() ?: ""
            val id = pokemon.url.substring(34, pokemon.url.lastIndex)
            textViewPokemonId.text = id ?: ""

            cardView.setOnClickListener {
                var bundle = Bundle()
                bundle.putString("id",id)
                val fragment = PokemonListFragment()
                fragment.setArguments(bundle)
                context.supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()
            }

        }
    }
}