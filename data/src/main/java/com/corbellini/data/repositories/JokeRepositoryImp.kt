package com.corbellini.data.repositories

import com.corbellini.data.remote.JokeService
import com.corbellini.domain.model.Joke
import com.corbellini.domain.repositories.JokeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class JokeRepositoryImp(private val jokeService: JokeService) : JokeRepository {

    override fun getRandom(): Flow<Joke> = flow {
        emit(jokeService.getRandom())
    }.map {  r -> r.toModel() }
}