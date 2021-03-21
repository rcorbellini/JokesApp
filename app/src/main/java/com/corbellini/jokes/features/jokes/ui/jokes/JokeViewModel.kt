package com.corbellini.jokes.features.jokes.ui.jokes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corbellini.jokes.features.jokes.domain.usecases.GetRandomJokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(
    private val getRandomJoke: GetRandomJokeUseCase
) : ViewModel() {


    private val _jokes = MutableStateFlow<List<JokeView>>(mutableListOf())
    val jokes get() = _jokes

    init {
        Timber.i("Init ViewModel")
        randomJoke()
    }

    fun dispatchRandomJoke() {
        randomJoke()
    }

    private fun randomJoke() {
        val list: MutableList<JokeView> = mutableListOf()
        list.addAll(_jokes.value)
        getRandomJoke.call().flowOn(Dispatchers.IO)
            .onEach { jokeModel ->
                _jokes.value = mutableListOf<JokeView>().apply {
                    addAll(_jokes.value)
                    add(jokeModel.toView())
                }
            }
            .catch { e -> e.printStackTrace() }
            .launchIn(viewModelScope)
    }

}