package com.corbellini.jokes.features.jokes.data.local.entities

import com.corbellini.jokes.features.jokes.domain.model.Joke
import java.util.*

data class JokeCacheEntity(
    val id: String,
    val categories: List<String>,
    val createdAt: Date,
    val iconUrl: String,
    val updatedAt: Date,
    val url: String,
    val value: String
    ){


    public  fun toModel() = Joke(
        id = id,
        categories= categories,
        createdAt = createdAt,
        iconUrl=  iconUrl,
        updatedAt = updatedAt,
        url = url,
        value = value
    )
}


private fun Joke.toEnity() = JokeCacheEntity(
    id = id,
    categories= categories,
    createdAt = createdAt,
    iconUrl=  iconUrl,
    updatedAt = updatedAt,
    url = url,
    value = value
)