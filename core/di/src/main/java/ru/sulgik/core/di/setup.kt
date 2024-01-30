package ru.sulgik.core.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.sulgik.core.data.graphQlModule

fun Application.startDI() {
    startKoin {
        androidLogger()
        androidContext(this@startDI)
        modules(
            graphQlModule
        )
    }
}