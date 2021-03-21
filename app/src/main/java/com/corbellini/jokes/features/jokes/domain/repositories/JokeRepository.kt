package com.corbellini.jokes.features.jokes.domain.repositories

import com.corbellini.jokes.features.jokes.domain.model.Joke
import kotlinx.coroutines.flow.Flow

interface JokeRepository{
    fun getRandom(): Flow<Joke>
}