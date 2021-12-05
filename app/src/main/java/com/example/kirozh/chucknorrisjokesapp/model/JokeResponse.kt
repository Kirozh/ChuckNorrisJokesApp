package com.example.kirozh.chucknorrisjokesapp.model

import com.example.kirozh.chucknorrisjokesapp.model.entities.Joke

/**
 * @author Kirill Ozhigin on 20.11.2021
 */
data class JokeResponse(
    val type: String,
    val value: List<Joke>
)