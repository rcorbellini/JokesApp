package com.corbellini.jokes.features.jokes.data.remote.entities

import com.corbellini.jokes.features.jokes.domain.model.Joke
import io.mockk.every
import io.mockk.mockkStatic
import org.junit.Assert
import org.junit.Test
import java.util.*

class JokeRemoteEntityTest {

    @Test
    fun `JokeRemoteEntity new Instance attribute check`(){
        //arrange
        val id = "id"
        val categories = emptyList<String>()
        val createdAt = Date()
        val iconUrl = "iconUrl"
        val updatedAt = Date()
        val url = "url"
        val value = "value"

        //act
        val result = JokeRemoteEntity(
            id = id,
            categories = categories,
            iconUrl =  iconUrl,
            updatedAt = updatedAt,
            createdAt = createdAt,
            url = url,
            value =  value,
        )

        //assert
        Assert.assertEquals(result.id, id)
        Assert.assertEquals(result.categories, categories)
        Assert.assertEquals(result.iconUrl, iconUrl)
        Assert.assertEquals(result.updatedAt, updatedAt)
        Assert.assertEquals(result.createdAt, createdAt)
        Assert.assertEquals(result.url, url)
        Assert.assertEquals(result.value, value)
    }


    @Test
    fun `JokeRemoteEntity#toModel must return the Model version of remote, with a random iconUrl`(){
        //arrange
        val id = "id"
        val categories = emptyList<String>()
        val createdAt = Date()
        val iconUrl = "iconUrl"
        val updatedAt = Date()
        val url = "url"
        val value = "value"

        val newIconUrl = "newUrl"


        mockkStatic(::randomUrlImage)
        every { randomUrlImage() } returns newIconUrl

        //act
        val result = JokeRemoteEntity(
            id = id,
            categories = categories,
            iconUrl =  iconUrl,
            updatedAt = updatedAt,
            createdAt = createdAt,
            url = url,
            value =  value,
        ).toModel()

        //assert
        Assert.assertEquals(result.iconUrl, newIconUrl)
    }



    @Test
    fun `Joke#toView must return the View version of Model`(){
        //arrange
        val id = "id"
        val categories = emptyList<String>()
        val createdAt = Date()
        val iconUrl = "iconUrl"
        val updatedAt = Date()
        val url = "url"
        val value = "value"

        val joke = JokeRemoteEntity(
            id = id,
            categories = categories,
            iconUrl =  iconUrl,
            updatedAt = updatedAt,
            createdAt = createdAt,
            url = url,
            value =  value,
        )

        //act
        val result = Joke(
            id = id,
            categories = categories,
            iconUrl =  iconUrl,
            updatedAt = updatedAt,
            createdAt = createdAt,
            url = url,
            value =  value,
        ).toEnity()

        //assert
        Assert.assertEquals(result, joke)
    }
}