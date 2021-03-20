package com.corbellini.jokes.features.jokes.ui.jokes

import androidx.compose.runtime.Immutable
import com.corbellini.jokes.features.jokes.domain.model.Joke
import java.util.*

@Immutable
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