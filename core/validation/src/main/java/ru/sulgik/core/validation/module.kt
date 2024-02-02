package ru.sulgik.core.validation

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.sulgik.core.validation.card.CardInputFormatter
import ru.sulgik.core.validation.card.CardInputValidator
import ru.sulgik.core.validation.card.RawCardInputFormatter
import ru.sulgik.core.validation.card.RegexCardInputValidator

val validationModule = module {
    singleOf(::RawCardInputFormatter) bind CardInputFormatter::class
    singleOf(::RegexCardInputValidator) bind CardInputValidator::class
}