package com.example.kirozh.chucknorrisjokesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kirozh.chucknorrisjokesapp.repositories.JokeRepository

/**
 * @author Kirill Ozhigin on 19.11.2021
 */
class MainViewModelFactory(private val repository: JokeRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
