package com.corbellini.jokes.features.jokes.domain.usecases
import com.corbellini.jokes.features.jokes.domain.model.Joke
import com.corbellini.jokes.features.jokes.domain.repositories.JokeRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

import java.util.*


class GetRandomJokeUseCaseTest{

    @Mock
    lateinit var jokeRepositoryMock : JokeRepository
    lateinit var useCaseImp : GetRandomJokeUseCaseImp

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        useCaseImp = GetRandomJokeUseCaseImp(jokeRepository = jokeRepositoryMock)
    }


    @Test
    fun `GetRandomJokeUseCase#call must call Repository`() {
        runBlocking{
            val id = "id"
            val categories = emptyList<String>()
            val createdAt = Date()
            val iconUrl = "iconUrl"
            val updatedAt = Date()
            val url = "url"
            val value = "value"

            //arrange
            val joke = Joke(
                id = id,
                categories = categories,
                iconUrl =  iconUrl,
                updatedAt = updatedAt,
                createdAt = createdAt,
                url = url,
                value =  value,
            )
            Mockito.doReturn(
                flow {
                    emit(joke)
                }
            ).`when`(jokeRepositoryMock).getRandom()

            //act
            val result = useCaseImp.call().toList()

            //assert
            verify(jokeRepositoryMock).getRandom()
        }
    }

    @Test
    fun `GetRandomJokeUseCase#call must return the Repository return`() = runBlocking{
        //arrange
        val id = "id"
        val categories = emptyList<String>()
        val createdAt = Date()
        val iconUrl = "iconUrl"
        val updatedAt = Date()
        val url = "url"
        val value = "value"


        val joke = Joke(
            id = id,
            categories = categories,
            iconUrl =  iconUrl,
            updatedAt = updatedAt,
            createdAt = createdAt,
            url = url,
            value =  value,
        )
        Mockito.doReturn(
            flow {
                emit(joke)
            }
        ).`when`(jokeRepositoryMock).getRandom()

        //act
        val result = useCaseImp.call().toList()

        //assert
        Assert.assertEquals(result[0], joke)
    }
}