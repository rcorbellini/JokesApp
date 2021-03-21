package com.corbellini.jokes.features.jokes.data.repositories

import com.corbellini.jokes.features.jokes.data.remote.JokeService
import com.corbellini.jokes.features.jokes.domain.model.Joke
import com.corbellini.jokes.features.jokes.domain.repositories.JokeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class JokeRepositoryImp(private val jokeService: JokeService) : JokeRepository {

    override fun getRandom(): Flow<Joke> = flow {
        emit(jokeService.getRandom())
    }.map {  r -> r.toModel() }
}