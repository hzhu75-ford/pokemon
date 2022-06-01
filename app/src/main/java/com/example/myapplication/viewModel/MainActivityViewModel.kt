package com.example.myapplication.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.data.Pokemon
import com.example.myapplication.model.remote.NetworkCallOutput
import com.example.myapplication.repositories.MainRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel@Inject constructor(private val repository: MainRepositoryImpl): ViewModel() {


        private val _data: MutableLiveData<NetworkCallOutput<List<Pokemon>>> = MutableLiveData(NetworkCallOutput.Success(
            emptyList()))
        val data: LiveData<NetworkCallOutput<List<Pokemon>>> get() = _data
        init{getAllPokemon()}

    fun getAllPokemon() {
            _data.value = NetworkCallOutput.Loading
            viewModelScope.launch {
                val result = try {
                    repository.getPokemon()
                } catch (e: Exception) {
                    NetworkCallOutput.Error(e.message ?: "Unknown error")
                } finally {
                    Log.i("MainViewModel","Failed to Load Pokemon")
                }
                _data.postValue(result)
            }
        }
    }
