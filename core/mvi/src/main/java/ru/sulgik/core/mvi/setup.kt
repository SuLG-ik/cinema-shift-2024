package ru.sulgik.core.mvi

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val mviKotlinModule = module {
    singleOf(::createStoreFactory)
}

fun createStoreFactory(): StoreFactory {
    return DefaultStoreFactory()
}

