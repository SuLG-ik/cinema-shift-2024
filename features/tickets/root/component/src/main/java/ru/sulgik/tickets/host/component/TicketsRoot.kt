package ru.sulgik.tickets.host.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface TicketsRoot {

    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {

        data class SeanceSelector(val component: ru.sulgik.tickets.schedule.component.SeanceSelector) : Child

    }
}