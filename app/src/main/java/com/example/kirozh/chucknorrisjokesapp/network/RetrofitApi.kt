package com.example.kirozh.chucknorrisjokesapp.network

import com.example.kirozh.chucknorrisjokesapp.model.JokeResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Kirill Ozhigin on 19.11.2021
 */
interface RetrofitApi {


    @GET("{jokeNum}")
    suspend fun getJokesUsingCoroutines(@Path("jokeNum") jokeNum: Int): JokeResponse

    companion object {
        val BASE_URL: String = "https://api.icndb.com/jokes/random/"
        var retrofitService: RetrofitApi? = null

        fun getInstance(): RetrofitApi {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                retrofitService = retrofit.create(RetrofitApi::class.java)

            }
            return retrofitService!!
        }
    }
}