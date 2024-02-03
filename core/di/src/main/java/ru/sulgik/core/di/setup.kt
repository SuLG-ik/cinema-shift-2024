package ru.sulgik.core.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.sulgik.card.presentation.cardInputPresentationModule
import ru.sulgik.core.datetime.dateTimeModule
import ru.sulgik.core.graphql.data.graphQlModule
import ru.sulgik.core.images.imageURLFormatter
import ru.sulgik.core.ktor.data.ktorModule
import ru.sulgik.core.mvi.mviKotlinModule
import ru.sulgik.core.validation.validationModule
import ru.sulgik.filminfo.filmInfoModule
import ru.sulgik.filmlist.filmListModule
import ru.sulgik.login.loginModule
import ru.sulgik.tickets.places.ticketsPlacesSelectorModule
import ru.sulgik.tickets.schedule.ticketsSeanceSelectorModule
import ru.sulgik.tickets.ticketsSharedModule
import ru.sulgik.tickets.userinfo.ticketsUserInfoModule

fun Application.startDI() {
    startKoin {
        androidLogger()
        androidContext(this@startDI)
        modules(
            ktorModule,
            graphQlModule,
            imageURLFormatter,
            mviKotlinModule,
            dateTimeModule,
            validationModule,

            filmListModule,
            filmInfoModule,

            ticketsSharedModule,
            ticketsSeanceSelectorModule,
            ticketsPlacesSelectorModule,
            ticketsUserInfoModule,

            loginModule,

            cardInputPresentationModule,
        )
    }
}