package ru.sulgik.filmlist.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher

@OptIn(ExperimentalMviKotlinApi::class)
class FilmListStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
) : FilmListStore,
    Store<FilmListStore.Intent, FilmListStore.State, FilmListStore.Label> by storeFactory.create<_, Action, Message, _, _>(
        name = "FilmListStoreImpl",
        initialState = FilmListStore.State(),
        reducer = {
            when (it) {
                else -> TODO()
            }
        },
        bootstrapper = coroutineBootstrapper(coroutineDispatcher) { dispatch(Action.Setup) },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onAction<Action.Setup> {

            }
        },
    ) {

    sealed interface Action {
        data object Setup : Action
    }

    sealed interface Message {

    }
}