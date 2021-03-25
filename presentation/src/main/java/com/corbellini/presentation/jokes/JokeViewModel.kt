package com.corbellini.presentation.jokes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corbellini.domain.features.jokes.usecases.GetRandomJokeUseCase
import com.corbellini.presentation.R
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

    private val _loading = MutableStateFlow(true)
    val loading get() = _loading


    private val _error = MutableStateFlow<Int?>(null)
    val error get() = _error

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
            .onStart {  _loading.value = true
                        _error.value = null}
            .onEach { jokeModel ->
                _jokes.value = mutableListOf<JokeView>().apply {
                    addAll(_jokes.value)
                    add(jokeModel.toView())
                }
            }
            .onCompletion {
                _loading.value = false }
            .catch { _error.value = R.string.on_error}
            .launchIn(viewModelScope)
    }

}