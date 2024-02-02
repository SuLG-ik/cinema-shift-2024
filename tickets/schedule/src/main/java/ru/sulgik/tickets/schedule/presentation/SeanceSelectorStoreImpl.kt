package ru.sulgik.tickets.schedule.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import ru.sulgik.tickets.domain.entity.Schedule
import ru.sulgik.tickets.domain.entity.SelectedSeance
import ru.sulgik.tickets.schedule.domain.IsSelectedSeanceEqualsUseCase
import ru.sulgik.tickets.schedule.domain.SelectSeanceUseCase

@OptIn(ExperimentalMviKotlinApi::class)
class SeanceSelectorStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    isSelectedSeanceEqualsUseCase: IsSelectedSeanceEqualsUseCase,
    selectSeanceUseCase: SelectSeanceUseCase,
) : SeanceSelectorStore,
    Store<SeanceSelectorStore.Intent, SeanceSelectorStore.State, SeanceSelectorStore.Label> by storeFactory.create<_, _, Message, _, _>(
        name = "FilmScheduleStoreImpl",
        initialState = SeanceSelectorStore.State(),
        reducer = {
            when (it) {
                is Message.Select -> copy(
                    isContinueAvailable = true,
                    selectedSeance = it.seance,
                )

                is Message.Unselect -> copy(
                    isContinueAvailable = false,
                    selectedSeance = null,
                )

                is Message.UpdateSchedule -> copy(
                    schedule = it.schedule,
                )
            }
        },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onIntent<SeanceSelectorStore.Intent.UpdateScheduleList> {
                dispatch(Message.UpdateSchedule(it.schedule))
            }
            onIntent<SeanceSelectorStore.Intent.Select> {
                val selectedSeance = state().selectedSeance
                if (selectedSeance == null || !isSelectedSeanceEqualsUseCase(
                        selectedSeance = selectedSeance,
                        seance = it.seance
                    )
                )
                    dispatch(Message.Select(selectSeanceUseCase(it.seance)))
                else
                    dispatch(Message.Unselect)
            }
        },
    ) {

    sealed interface Message {
        data class UpdateSchedule(
            val schedule: List<Schedule>,
        ) : Message

        data class Select(
            val seance: SelectedSeance,
        ) : Message

        data object Unselect : Message
    }
}