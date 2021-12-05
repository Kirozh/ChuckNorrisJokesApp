package com.example.kirozh.chucknorrisjokesapp.repositories

import com.example.kirozh.chucknorrisjokesapp.network.RetrofitApi

/**
 * @author Kirill Ozhigin on 20.11.2021
 */
class JokeRepository(val retrofitApi: RetrofitApi) {
    suspend fun getJokesUsingCoroutines(mNum: Int)
    = retrofitApi.getJokesUsingCoroutines(mNum).value

}