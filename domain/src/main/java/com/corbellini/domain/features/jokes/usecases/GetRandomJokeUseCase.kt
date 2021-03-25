package com.corbellini.domain.features.jokes.usecases
import com.corbellini.domain.features.jokes.model.Joke
import com.corbellini.domain.features.jokes.repositories.JokeRepository
import kotlinx.coroutines.flow.Flow

interface GetRandomJokeUseCase {
    fun call(): Flow<Joke>
}

open class GetRandomJokeUseCaseImp
    (private val jokeRepository: JokeRepository) : GetRandomJokeUseCase {


    override fun call(): Flow<Joke>  = jokeRepository.getRandom()
}
