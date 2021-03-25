package com.corbellini.presentation.jokes

import com.corbellini.domain.features.jokes.model.Joke
import java.util.*

data class JokeView(
    val id: String,
    val categories: List<String>,
    val createdAt: Date,
    val iconUrl: String,
    val updatedAt: Date,
    val url: String,
    val value: String
) {

    fun toModel() = Joke(
        id = id,
        categories = categories,
        createdAt = createdAt,
        iconUrl = iconUrl,
        updatedAt = updatedAt,
        url = url,
        value = value
    )
}

fun Joke.toView() = JokeView(
    id = id,
    categories = categories,
    createdAt = createdAt,
    iconUrl = iconUrl,
    updatedAt = updatedAt,
    url = url,
    value = value
)


