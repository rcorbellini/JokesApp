package com.corbellini.jokes.features.jokes.domain.usecases
import com.corbellini.jokes.features.jokes.domain.model.Joke
import com.corbellini.jokes.features.jokes.domain.repositories.JokeRepository
import kotlinx.coroutines.flow.Flow

interface ListRandomJokesUseCase {
    fun call(param: ParamsRandomJoke?  = null): Flow<List<Joke>>
}

open class ListRandomJokesUseCaseImp
    (private val jokeRepository: JokeRepository) : ListRandomJokesUseCase {


    override fun call(param: ParamsRandomJoke? ): Flow<List<Joke>>  = jokeRepository.listRandom()
}

data class ParamsRandomJoke(val category: String?)