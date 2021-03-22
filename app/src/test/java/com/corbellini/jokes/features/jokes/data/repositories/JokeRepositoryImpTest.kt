package com.corbellini.jokes.features.jokes.data.repositories

import com.corbellini.jokes.features.jokes.data.remote.JokeService
import com.corbellini.jokes.features.jokes.data.remote.entities.JokeRemoteEntity
import com.corbellini.jokes.features.jokes.data.remote.entities.randomUrlImage
import com.corbellini.jokes.features.jokes.domain.model.Joke
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*

class JokeRepositoryImpTest {

    @Mock
    lateinit var jokeServiceMock : JokeService
    lateinit var jokeRepositoryImp: JokeRepositoryImp

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        jokeRepositoryImp = JokeRepositoryImp(jokeService = jokeServiceMock)
    }


    @Test
    fun `JokeRepositoryImp#getRandom must call Service`() {
        runBlocking{
            val id = "id"
            val categories = emptyList<String>()
            val createdAt = Date()
            val iconUrl = "iconUrl"
            val updatedAt = Date()
            val url = "url"
            val value = "value"

            //arrange
            val joke = JokeRemoteEntity(
                id = id,
                categories = categories,
                iconUrl =  iconUrl,
                updatedAt = updatedAt,
                createdAt = createdAt,
                url = url,
                value =  value,
            )
            Mockito.doReturn(
                joke
            ).`when`(jokeServiceMock).getRandom()

            //act
            val result = jokeRepositoryImp.getRandom().toList()

            //assert
            Mockito.verify(jokeServiceMock).getRandom()
        }
    }

    @Test
    fun `JokeRepositoryImp#getRandom must return mapped entity from the Service`() = runBlocking{
        //arrange
        val id = "id"
        val categories = emptyList<String>()
        val createdAt = Date()
        val iconUrl = "iconUrl"
        val updatedAt = Date()
        val url = "url"
        val value = "value"

        val newIconUrl = "newUrl"

        val jokeEntity = JokeRemoteEntity(
             id = id,
            categories = categories,
            iconUrl =  iconUrl,
            updatedAt = updatedAt,
            createdAt = createdAt,
            url = url,
            value =  value,
        )
        val joke = Joke(
            id = id,
            categories = categories,
            iconUrl =  newIconUrl,
            updatedAt = updatedAt,
            createdAt = createdAt,
            url = url,
            value =  value,
        )
        Mockito.doReturn(
            jokeEntity
        ).`when`(jokeServiceMock).getRandom()

        mockkStatic(::randomUrlImage)
        every { randomUrlImage() } returns newIconUrl


        //act
        val result = jokeRepositoryImp.getRandom().toList()[0]

        //assert
        Assert.assertEquals(result, joke)

    }

}