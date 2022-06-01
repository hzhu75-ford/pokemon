package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemAbilityBinding
import com.example.myapplication.databinding.ItemPokemonsBinding
import com.example.myapplication.model.data.Ability
import com.example.myapplication.model.data.Pokemon
import com.example.myapplication.model.data.SinglePokeDetails

class AbilitiesListAdapter : RecyclerView.Adapter<AbilitiesListAdapter.AbilitiesViewHolder>() {
    class AbilitiesViewHolder (private val binding:ItemAbilityBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(ability: Ability) {
            binding.apply{
                if ( !ability.is_hidden){
                    textViewAbilitySlot.text = ability.slot.toString()
                    textViewAbilityName.text = ability.ability.name
                    textViewAbilityUrl.text = ability.ability.url
                }
                else{
                    textViewAbilityUrl.visibility = View.GONE
                    textViewAbilityName.visibility = View.GONE
                    textViewAbilitySlot.visibility = View.GONE
                }

            }

        }

    }

    var abilitiesList : List<Ability> = listOf()

    fun setDataList(singlePokeDetails: SinglePokeDetails){
        this.abilitiesList = singlePokeDetails.abilities
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilitiesViewHolder {
        val binding = ItemAbilityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AbilitiesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AbilitiesViewHolder, position: Int) {
        holder.bind(abilitiesList[position])
    }

    override fun getItemCount(): Int {
        return abilitiesList.size
    }

}
