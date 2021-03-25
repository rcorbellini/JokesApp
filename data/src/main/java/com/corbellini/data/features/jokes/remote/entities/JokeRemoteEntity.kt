package com.corbellini.data.features.jokes.remote.entities

import com.corbellini.domain.features.jokes.model.Joke
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
        iconUrl=  randomUrlImage(),
        updatedAt = updatedAt,
        url = url,
        value = value
    )
}


fun Joke.toEnity() = JokeRemoteEntity(
    id = id,
    categories= categories,
    createdAt = createdAt,
    iconUrl=  iconUrl,
    updatedAt = updatedAt,
    url = url,
    value = value
)

//Icon Url que vem do servidor nao fica bacana, emulando uma photo (url) pra usar de fundo.
fun randomUrlImage() = when(Random().nextInt(5)){
    1-> "https://noticias.gospelmais.com.br/files/2015/04/chuck-norris.jpg"
    2-> "https://www.pecsma.hu/wp-content/uploads/2018/11/Chuck5.png"
    3-> "https://dfw.cbslocal.com/wp-content/uploads/sites/15909545/2010/12/walker.jpg"
    4-> "https://funtastik.fr/wp-content/uploads/2018/06/chuck-norris-facts.jpg"
    5-> "https://supanova-wpengine.netdna-ssl.com/wp-content/uploads/2018/05/Chuck_Norris_03-1024x824.jpg"
    else -> "https://vistapointe.net/images/chuck-norris-1.jpg"
}