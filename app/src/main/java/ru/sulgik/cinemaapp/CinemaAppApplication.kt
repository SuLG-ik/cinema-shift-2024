package ru.sulgik.cinemaapp

import android.app.Application
import ru.sulgik.core.di.startDI

class CinemaAppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startDI()
    }

}