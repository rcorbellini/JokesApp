package com.corbellini.domain.repositories

import com.corbellini.domain.model.Joke
import kotlinx.coroutines.flow.Flow

interface JokeRepository{
    fun getRandom(): Flow<Joke>
}