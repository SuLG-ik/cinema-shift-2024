package ru.sulgik.filmlist.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.sulgik.filmlist.domain.entity.Film
import ru.sulgik.filmlist.domain.usecase.LoadFilmListUseCase

@OptIn(ExperimentalMviKotlinApi::class)
class FilmListStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    loadFilmListUseCase: LoadFilmListUseCase,
) : FilmListStore,
    Store<FilmListStore.Intent, FilmListStore.State, FilmListStore.Label> by storeFactory.create<_, Action, Message, _, _>(
        name = "FilmListStoreImpl",
        initialState = FilmListStore.State(),
        reducer = {
            when (it) {
                is Message.SetFilmList -> copy(
                    isLoading = false,
                    films = it.films,
                )
            }
        },
        bootstrapper = coroutineBootstrapper(coroutineDispatcher) { dispatch(Action.Setup) },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onAction<Action.Setup> {
                launch {
                    val filmList = loadFilmListUseCase()
                    withContext(Dispatchers.Main) {
                        dispatch(Message.SetFilmList(filmList))
                    }
                }
            }
        },
    ) {

    sealed interface Action {
        data object Setup : Action
    }

    sealed interface Message {
        data class SetFilmList(val films: List<Film>) : Message
    }
}