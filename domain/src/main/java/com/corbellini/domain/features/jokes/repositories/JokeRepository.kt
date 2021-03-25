package com.corbellini.domain.features.jokes.repositories

import com.corbellini.domain.features.jokes.model.Joke
import kotlinx.coroutines.flow.Flow

interface JokeRepository{
    fun getRandom(): Flow<Joke>
}