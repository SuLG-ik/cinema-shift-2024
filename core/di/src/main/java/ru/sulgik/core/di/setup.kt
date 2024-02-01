package ru.sulgik.core.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.sulgik.core.data.graphQlModule
import ru.sulgik.core.datetime.dateTimeModule
import ru.sulgik.core.images.imageURLFormatter
import ru.sulgik.core.mvi.mviKotlinModule
import ru.sulgik.filminfo.data.filmInfoDataModule
import ru.sulgik.filminfo.domain.filmInfoDomainModule
import ru.sulgik.filminfo.presentation.filmInfoPresentationModule
import ru.sulgik.filmlist.data.filmListDataModule
import ru.sulgik.filmlist.domain.filmListDomainModule
import ru.sulgik.filmlist.presentation.filmListPresentationModule
import ru.sulgik.tickets.data.ticketsDataModule
import ru.sulgik.tickets.domain.ticketsDomainModule
import ru.sulgik.tickets.places.presentation.placesSelectorPresentationModule
import ru.sulgik.tickets.presentation.ticketsPresentationModule
import ru.sulgik.tickets.schedule.presentation.seanceSelectorPresentationModule

fun Application.startDI() {
    startKoin {
        androidLogger()
        androidContext(this@startDI)
        modules(
            graphQlModule,
            imageURLFormatter,
            mviKotlinModule,
            dateTimeModule,

            filmListDataModule,
            filmListDomainModule,
            filmListPresentationModule,

            filmInfoDataModule,
            filmInfoPresentationModule,
            filmInfoDomainModule,

            ticketsDomainModule,
            ticketsPresentationModule,
            ticketsDataModule,
            seanceSelectorPresentationModule,
            placesSelectorPresentationModule,
        )
    }
}