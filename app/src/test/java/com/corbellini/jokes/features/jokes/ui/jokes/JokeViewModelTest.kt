package com.corbellini.jokes.features.jokes.ui.jokes

import app.cash.turbine.test
import com.corbellini.jokes.features.jokes.domain.model.Joke
import com.corbellini.jokes.features.jokes.domain.usecases.GetRandomJokeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.verify
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.mockito.Mockito.times
import java.util.*
import kotlin.time.ExperimentalTime
import kotlin.time.seconds

class JokeViewModelTest {

    private lateinit var jokeViewModel: JokeViewModel
    @Mock
    private lateinit var getRandomJokeUseCaseMock: GetRandomJokeUseCase

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp(){
        Dispatchers.setMain(testDispatcher)

        MockitoAnnotations.openMocks(this)
        jokeViewModel = JokeViewModel(getRandomJoke = getRandomJokeUseCaseMock)

        MockitoAnnotations.openMocks(this)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @ExperimentalTime
    @Test
    fun `JokeViewModel on Init must sort some Joke`(){
        runBlocking {
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
                iconUrl = iconUrl,
                updatedAt = updatedAt,
                createdAt = createdAt,
                url = url,
                value = value,
            )
            val jokeView = JokeView(
                id = id,
                categories = categories,
                iconUrl = iconUrl,
                updatedAt = updatedAt,
                createdAt = createdAt,
                url = url,
                value = value,
            )


            Mockito.doReturn(
                flow {
                    emit(joke)
                }
            ).`when`(getRandomJokeUseCaseMock).call()

            //act
            jokeViewModel = JokeViewModel(getRandomJoke = getRandomJokeUseCaseMock)
            val result  = jokeViewModel.jokes
            //assert
            verify(getRandomJokeUseCaseMock).call()
            result.test {
                    Assert.assertEquals(jokeView , expectItem().first())
                }
        }
    }

    @ExperimentalTime
    @Test
    fun `JokeViewModel#dispatchRandomJoke must sort some Joke`(){
        runBlocking {
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
                iconUrl = iconUrl,
                updatedAt = updatedAt,
                createdAt = createdAt,
                url = url,
                value = value,
            )
            val jokeView = JokeView(
                id = id,
                categories = categories,
                iconUrl = iconUrl,
                updatedAt = updatedAt,
                createdAt = createdAt,
                url = url,
                value = value,
            )


            Mockito.doReturn(
                flow {
                    emit(joke)
                }
            ).`when`(getRandomJokeUseCaseMock).call()

            //act
            jokeViewModel = JokeViewModel(getRandomJoke = getRandomJokeUseCaseMock)
            jokeViewModel.dispatchRandomJoke()
            val result  = jokeViewModel.jokes

            //assert
            verify(getRandomJokeUseCaseMock, times(2)).call()
            result.test{
                Assert.assertEquals(jokeView ,  expectItem().first())
            }
        }
    }


}