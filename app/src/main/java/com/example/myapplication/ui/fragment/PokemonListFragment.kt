package com.example.myapplication.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.PokemonListAdapter
import com.example.myapplication.databinding.FragmentPokemonListBinding
import com.example.myapplication.model.remote.NetworkCallOutput
import com.example.myapplication.ui.MainActivity
import com.example.myapplication.viewModel.MainActivityViewModel
import com.google.android.material.snackbar.Snackbar

class PokemonListFragment: Fragment(R.layout.fragment_pokemon_list) {

    private val viewModel: MainActivityViewModel by viewModels()
    lateinit var pokemonAdapter: PokemonListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentPokemonListBinding.bind(view)

        binding.apply {
            recyclerView.apply {
                adapter = pokemonAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                addItemDecoration(
                    DividerItemDecoration(
                        recyclerView.context,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }

        }

        viewModel.data.observe(viewLifecycleOwner, {
            when (it) {
                is NetworkCallOutput.Loading -> binding.progressBar.visibility = View.VISIBLE
                is NetworkCallOutput.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Log.e("Api error", it.error)
                    Snackbar.make(binding.recyclerView, it.error, Snackbar.LENGTH_LONG).show()
                }
                is NetworkCallOutput.Success -> {
                    binding.progressBar.visibility = View.GONE
                    pokemonAdapter.setDataList(it.data)
                }
            }
        })
    }
}