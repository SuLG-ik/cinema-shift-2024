package ru.sulgik.tickets.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.sulgik.tickets.domain.entity.Film
import ru.sulgik.tickets.domain.entity.Schedule
import ru.sulgik.tickets.domain.usecase.LoadFilmScheduleUseCase
import ru.sulgik.tickets.domain.usecase.LoadShortFilmUseCase

@OptIn(ExperimentalMviKotlinApi::class)
class FilmScheduleStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    loadFilmScheduleUseCase: LoadFilmScheduleUseCase,
    loadShortFilmUseCase: LoadShortFilmUseCase,
    params: FilmScheduleStore.Params,
) : FilmScheduleStore,
    Store<FilmScheduleStore.Intent, FilmScheduleStore.State, FilmScheduleStore.Label> by storeFactory.create<_, Action, Message, _, _>(
        name = "FilmScheduleStoreImpl",
        initialState = FilmScheduleStore.State(),
        reducer = {
            when (it) {
                is Message.SetScheduleList -> copy(
                    isLoading = false,
                    schedule = it.schedule,
                    film = it.film,
                )
            }
        },
        bootstrapper = coroutineBootstrapper(coroutineDispatcher) { dispatch(Action.Setup) },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onAction<Action.Setup> {
                launch {
                    val schedule = async { loadFilmScheduleUseCase(params.filmId) }
                    val film = async { loadShortFilmUseCase(params.filmId) }
                    withContext(Dispatchers.Main) {
                        dispatch(Message.SetScheduleList(film.await(), schedule.await()))
                    }
                }
            }
        },
    ) {

    sealed interface Action {
        data object Setup : Action
    }

    sealed interface Message {
        data class SetScheduleList(
            val film: Film,
            val schedule: List<Schedule>
        ) : Message
    }
}