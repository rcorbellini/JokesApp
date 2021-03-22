package com.corbellini.jokes.features.jokes.ui.jokes

import com.corbellini.jokes.features.jokes.domain.model.Joke
import org.junit.Assert
import org.junit.Test
import java.util.*

class JokeViewTest {
    @Test
    fun `JokeView new Instance attribute check`(){
        //arrange
        val id = "id"
        val categories = emptyList<String>()
        val createdAt = Date()
        val iconUrl = "iconUrl"
        val updatedAt = Date()
        val url = "url"
        val value = "value"

        //act
        val result = JokeView(
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
    fun `JokeView#toModel must return the Model version of view`(){
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

        //act
        val result = JokeView(
            id = id,
            categories = categories,
            iconUrl =  iconUrl,
            updatedAt = updatedAt,
            createdAt = createdAt,
            url = url,
            value =  value,
        ).toModel()

        //assert
        Assert.assertEquals(result, joke)
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

        val joke = JokeView(
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
        ).toView()

        //assert
        Assert.assertEquals(result, joke)
    }

}