package com.corbellini.jokes.features.jokes.domain.model

import java.util.*

data class Joke(
    val id: String,
    val categories: List<String>,
    val createdAt: Date,
    val iconUrl: String,
    val updatedAt: Date,
    val url: String,
    val value: String
    )
