package com.corbellini.jokes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corbellini.jokes.features.jokes.domain.usecases.ListRandomJokesUseCase
import com.corbellini.jokes.features.jokes.ui.jokes.JokeView
import com.corbellini.jokes.features.jokes.ui.jokes.toView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainTestViewModel @Inject constructor(
    private val listRandomJokes: ListRandomJokesUseCase
) : ViewModel() {


    private val _jokes = MutableStateFlow(emptyList<JokeView>())
    val jokes get() = _jokes

    init {
        Timber.i("Init ViewModel")
        listRandomJokes.call().flowOn(Dispatchers.IO)
            .onEach { _jokes.value = it.map { j -> j.toView()} }
            .catch { e -> e.printStackTrace() }
            .launchIn(viewModelScope)
    }
}