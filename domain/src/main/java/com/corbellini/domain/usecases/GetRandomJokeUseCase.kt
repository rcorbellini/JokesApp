package com.corbellini.domain.usecases

import com.corbellini.domain.model.Joke
import com.corbellini.domain.repositories.JokeRepository
import kotlinx.coroutines.flow.Flow

interface GetRandomJokeUseCase {
    fun call(): Flow<Joke>
}

open class GetRandomJokeUseCaseImp
    (private val jokeRepository: JokeRepository) : GetRandomJokeUseCase {


    override fun call(): Flow<Joke>  = jokeRepository.getRandom()
}
