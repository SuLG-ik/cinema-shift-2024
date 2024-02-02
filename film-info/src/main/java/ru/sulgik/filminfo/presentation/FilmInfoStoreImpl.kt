package ru.sulgik.filminfo.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.sulgik.filminfo.domain.entity.Film
import ru.sulgik.filminfo.domain.usecase.LoadFilmInfoUseCase

@OptIn(ExperimentalMviKotlinApi::class)
class FilmInfoStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    loadFilmInfoUseCase: LoadFilmInfoUseCase,
    params: FilmInfoStore.Params,
) : FilmInfoStore,
    Store<FilmInfoStore.Intent, FilmInfoStore.State, FilmInfoStore.Label> by storeFactory.create<_, Action, Message, _, _>(
        name = "FilmInfoStoreImpl",
        initialState = FilmInfoStore.State(),
        reducer = {
            when (it) {
                is Message.SetFilmList -> copy(
                    isLoading = false,
                    film = it.film,
                )
            }
        },
        bootstrapper = coroutineBootstrapper(coroutineDispatcher) { dispatch(Action.Setup) },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onAction<Action.Setup> {
                launch {
                    val film = loadFilmInfoUseCase(params.filmId)
                    withContext(Dispatchers.Main) {
                        dispatch(Message.SetFilmList(film))
                    }
                }
            }
        },
    ) {

    sealed interface Action {
        data object Setup : Action
    }

    sealed interface Message {
        data class SetFilmList(val film: Film) : Message
    }
}