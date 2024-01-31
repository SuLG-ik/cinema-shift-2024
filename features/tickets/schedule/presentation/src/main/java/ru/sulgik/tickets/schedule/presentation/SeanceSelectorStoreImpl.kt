package ru.sulgik.tickets.schedule.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import java.time.LocalDate
import java.time.LocalTime

@OptIn(ExperimentalMviKotlinApi::class)
class SeanceSelectorStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
) : SeanceSelectorStore,
    Store<SeanceSelectorStore.Intent, SeanceSelectorStore.State, SeanceSelectorStore.Label> by storeFactory.create<_, _, Message, _, _>(
        name = "FilmScheduleStoreImpl",
        initialState = SeanceSelectorStore.State(),
        reducer = {
            when (it) {
                is Message.Select -> copy(
                    selectedSeance = SeanceSelectorStore.State.SelectedSeance(
                        date = it.date,
                        time = it.time
                    )
                )

                is Message.Unselect -> copy(selectedSeance = null)
            }
        },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onIntent<SeanceSelectorStore.Intent.Select> {
                val selectedSeance = state().selectedSeance
                if (selectedSeance == null) {
                    dispatch(Message.Select(it.date, it.time))
                    return@onIntent
                }
                if (selectedSeance.time == it.time && selectedSeance.date == it.date)
                    dispatch(Message.Unselect)
                else
                    dispatch(Message.Select(it.date, it.time))
            }
        },
    ) {

    sealed interface Message {
        data class Select(val date: LocalDate, val time: LocalTime) : Message
        data object Unselect : Message
    }
}