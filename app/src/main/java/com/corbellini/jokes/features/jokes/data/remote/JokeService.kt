package com.corbellini.jokes.features.jokes.data.remote

import com.corbellini.jokes.features.jokes.data.remote.entities.JokeRemoteEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface JokeService {
    companion object {
        const val BASE_URL = "https://api.chucknorris.io"
    }

    @GET("/jokes/random")
    suspend fun getRandom(): JokeRemoteEntity

}