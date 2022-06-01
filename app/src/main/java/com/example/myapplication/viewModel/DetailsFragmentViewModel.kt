package com.example.myapplication.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.data.*
import com.example.myapplication.model.remote.NetworkCallOutput
import com.example.myapplication.repositories.MainRepositoryImpl
import kotlinx.coroutines.launch

class DetailsFragmentViewModel(private val repository: MainRepositoryImpl): ViewModel() {
    private val _data: MutableLiveData<NetworkCallOutput<SinglePokeDetails>>? = MutableLiveData(NetworkCallOutput.Success(
        SinglePokeDetails(emptyList(),
            0,
            emptyList(),
            0,
            0,
            emptyList(),
            "",
            0,
            Species("",""),
            null,
            null,
            emptyList(),
            emptyList(),
            0
        )))
    val data: LiveData<NetworkCallOutput<SinglePokeDetails>>? get() = _data

    fun getPokemonDetails(id:String) {
        _data?.value = NetworkCallOutput.Loading
        viewModelScope.launch {
            val result = try {
                repository.getSinglePokemon(id)
            } catch (e: Exception) {
                NetworkCallOutput.Error(e.message ?: "Unknown error")
            } finally {
                Log.i("DetailViewModel","Failed to Load Pokemon")
            }
            _data!!.postValue(result)
        }
    }
}
