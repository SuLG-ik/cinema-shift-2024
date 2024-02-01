package ru.sulgik.tickets.schedule.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import ru.sulgik.tickets.domain.entity.Schedule
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
                        time = it.time,
                        hall = it.hallType,
                    )
                )

                is Message.Unselect -> copy(selectedSeance = null)
            }
        },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onIntent<SeanceSelectorStore.Intent.Select> {
                val selectedSeance = state().selectedSeance
                if (selectedSeance == null || selectedSeance.time == it.time && selectedSeance.date == it.date && selectedSeance.hall == it.hall)
                    dispatch(Message.Unselect)

                dispatch(Message.Select(it.date, it.time, it.hall))
            }
        },
    ) {

    sealed interface Message {
        data class Select(
            val date: LocalDate,
            val time: LocalTime,
            val hallType: Schedule.HallType,
        ) : Message

        data object Unselect : Message
    }
}