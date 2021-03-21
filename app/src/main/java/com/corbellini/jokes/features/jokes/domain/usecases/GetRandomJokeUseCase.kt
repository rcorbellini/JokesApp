package com.corbellini.jokes.features.jokes.domain.usecases
import com.corbellini.jokes.features.jokes.domain.model.Joke
import com.corbellini.jokes.features.jokes.domain.repositories.JokeRepository
import kotlinx.coroutines.flow.Flow

interface GetRandomJokeUseCase {
    fun call(param: ParamsRandomJoke?  = null): Flow<Joke>
}

open class GetRandomJokeUseCaseImp
    (private val jokeRepository: JokeRepository) : GetRandomJokeUseCase {


    override fun call(param: ParamsRandomJoke? ): Flow<Joke>  = jokeRepository.getRandom()
}

data class ParamsRandomJoke(val category: String?)