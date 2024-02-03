package ru.sulgik.card.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import ru.sulgik.core.validation.card.CardCCVValidationResult
import ru.sulgik.core.validation.card.CardDataError
import ru.sulgik.core.validation.card.CardDateValidationResult
import ru.sulgik.core.validation.card.CardInputFormatter
import ru.sulgik.core.validation.card.CardInputValidator
import ru.sulgik.core.validation.card.CardNumberValidationResult

@OptIn(ExperimentalMviKotlinApi::class)
internal class CardInputStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    validator: CardInputValidator,
    formatter: CardInputFormatter
) : CardInputStore,
    Store<CardInputStore.Intent, CardInputStore.State, CardInputStore.Label> by storeFactory.create<_, _, Message, _, _>(
        name = "CardInputStoreImpl",
        initialState = CardInputStore.State(),
        reducer = {
            when (it) {
                is Message.CCVInput -> copy(
                    isContinueAvailable = it.result.error == null && card.date.error == null && card.number.error == null,
                    card = card.copy(
                        ccv = CardInputStore.State.Card.CardCCVField(
                            value = it.result.value,
                            error = it.result.error?.convert()
                        ),
                    )
                )

                is Message.DateInput -> copy(
                    isContinueAvailable = it.result.error == null && card.ccv.error == null && card.number.error == null,
                    card = card.copy(
                        date = CardInputStore.State.Card.CardDateField(
                            value = it.result.value,
                            error = it.result.error?.convert(),
                        ),
                    )
                )

                is Message.NumberInput -> copy(
                    isContinueAvailable = it.result.error == null && card.date.error == null && card.ccv.error == null,
                    card = card.copy(
                        number = CardInputStore.State.Card.CardNumberField(
                            value = it.result.value,
                            error = it.result.error?.convert()
                        ),
                    )
                )
            }
        },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onIntent<CardInputStore.Intent.NumberInput> {
                val formattedNumber = formatter.formatNumber(it.value)
                val validationResult = validator.validateNumber(formattedNumber)
                dispatch(Message.NumberInput(validationResult))
            }
            onIntent<CardInputStore.Intent.DateInput> {
                val formattedDate = formatter.formatDate(it.value)
                val validationResult = validator.validateDate(formattedDate)
                dispatch(Message.DateInput(validationResult))
            }
            onIntent<CardInputStore.Intent.CCVInput> { it ->
                val formattedCCV = formatter.formatCCV(it.value)
                val validationResult = validator.validateCCV(formattedCCV)
                dispatch(Message.CCVInput(validationResult))
            }
        },
    ) {

    sealed interface Message {
        data class NumberInput(val result: CardNumberValidationResult) : Message
        data class DateInput(val result: CardDateValidationResult) : Message
        data class CCVInput(val result: CardCCVValidationResult) : Message
    }
}

private fun CardDataError.convert(): CardInputStore.State.Card.Error {
    return when(this) {
        CardDataError.IncorrectLength -> CardInputStore.State.Card.Error.IncorrectLength
        CardDataError.IncorrectValue -> CardInputStore.State.Card.Error.IncorrectValue
    }
}
