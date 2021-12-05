package com.example.kirozh.chucknorrisjokesapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kirozh.chucknorrisjokesapp.model.entities.Joke
import com.example.kirozh.chucknorrisjokesapp.repositories.JokeRepository
import kotlinx.coroutines.*

/**
 * @author Kirill Ozhigin on 19.11.2021
 */
class MainViewModel(private var repository: JokeRepository) : ViewModel() {
    val jokeNum = MutableLiveData<Int>()
    val errorMessage = MutableLiveData<String>()
    val jokeList = MutableLiveData<List<Joke>>()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getChuckNorrisJokes(mNum: Int) {
        jokeNum.value = mNum
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val response = repository.getJokesUsingCoroutines(mNum)
            withContext(Dispatchers.Main) {
                try {
                    jokeList.postValue(response)
                }
                catch (e: Exception) {
                    onError(e.message.toString())
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.postValue(message)
    }
}