package ru.sulgik.tickets.host.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface TicketsRoot {

    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {

        data class SeanceSelector(val component: ru.sulgik.tickets.schedule.component.SeanceSelector) :
            Child

        data class PlacesSelector(val component: ru.sulgik.tickets.places.component.PlacesSelector) :
            Child

        data class OrderConfirmation(val component: ru.sulgik.tickets.confirmation.component.Confirmation) :
            Child

        data class CardInput(val component: ru.sulgik.card.component.CardInput) : Child

    }
}