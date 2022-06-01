package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.adapters.AbilitiesListAdapter
import com.example.myapplication.adapters.PokemonListAdapter
import com.example.myapplication.databinding.DetailFragmentBinding
import com.example.myapplication.model.remote.NetworkCallOutput
import com.example.myapplication.viewModel.DetailsFragmentViewModel
import com.example.myapplication.viewModel.MainActivityViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : DialogFragment() {
    private var id: String? = null
    private var _binding: DetailFragmentBinding? = null
    private val viewModel: DetailsFragmentViewModel by viewModels()
    lateinit var abilitiesList: AbilitiesListAdapter

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            id = it.getString(id)?:""
        }
        return inflater.inflate(
            R.layout.detail_fragment,
            container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DetailFragmentBinding.bind(view)
        init()
        initViewModel()
    }

    private fun init() {
        viewModel.getPokemonDetails(id!!)
        abilitiesList = AbilitiesListAdapter()
    }

    private fun initViewModel() = viewModel.data?.observe(viewLifecycleOwner, {
        when (it) {
            is NetworkCallOutput.Loading -> binding.progressBar.visibility = View.VISIBLE
            is NetworkCallOutput.Error -> {
                binding.progressBar.visibility = View.GONE
                Log.e("Api error", it.error)
                Snackbar.make(binding.recyclerView, it.error, Snackbar.LENGTH_LONG).show()
            }
            is NetworkCallOutput.Success -> {
                binding.progressBar.visibility = View.GONE
                abilitiesList.setDataList(it.data)
            }
        }
    })



override fun onDestroyView() {
    super.onDestroyView()

    _binding = null
}
}