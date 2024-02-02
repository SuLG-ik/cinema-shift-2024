package ru.sulgik.core.datetime

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dateTimeModule = module {
    singleOf(::DateTimeFormatterImpl) bind DateTimeFormatter::class
}