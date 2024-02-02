package ru.sulgik.core.mvi

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val mviKotlinModule = module {
    singleOf(::createStoreFactory)
    single { Dispatchers.IO } bind CoroutineDispatcher::class
}

fun createStoreFactory(): StoreFactory {
    return LoggingStoreFactory((DefaultStoreFactory()))
}

