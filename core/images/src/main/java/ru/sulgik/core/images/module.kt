package ru.sulgik.core.images

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val imageURLFormatter = module {
    single { ImageLoaderConfig(BuildConfig.REMOTE_URL) }
    singleOf(::ConfigurationImageURLFormatter)
}