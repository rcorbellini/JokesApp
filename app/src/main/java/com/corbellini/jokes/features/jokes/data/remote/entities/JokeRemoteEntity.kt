package com.corbellini.jokes.features.jokes.data.remote.entities

import com.corbellini.jokes.features.jokes.domain.model.Joke
import com.google.gson.annotations.SerializedName
import java.util.*

data class JokeRemoteEntity(
    val id: String,
    val categories: List<String>,
    @SerializedName("created_at")
    val createdAt: Date,
    @SerializedName("icon_url")
    val iconUrl: String,
    @SerializedName("updated_at")
    val updatedAt: Date,
    val url: String,
    val value: String
    ){


    fun toModel() = Joke(
        id = id,
        categories= categories,
        createdAt = createdAt,
        iconUrl=  iconUrl,
        updatedAt = updatedAt,
        url = url,
        value = value
    )
}


private fun Joke.toEnity() = JokeRemoteEntity(
    id = id,
    categories= categories,
    createdAt = createdAt,
    iconUrl=  iconUrl,
    updatedAt = updatedAt,
    url = url,
    value = value
)