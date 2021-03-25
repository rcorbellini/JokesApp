package com.corbellini.data.features.jokes.remote

import com.corbellini.data.features.jokes.remote.entities.JokeRemoteEntity
import retrofit2.http.GET

interface JokeService {
    companion object {
        const val BASE_URL = "https://api.chucknorris.io"
    }

    @GET("/jokes/random")
    suspend fun getRandom(): JokeRemoteEntity

}