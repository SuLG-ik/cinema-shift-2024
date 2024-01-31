package ru.sulgik.core.images

import android.content.Context
import coil3.ImageLoader
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import coil3.util.DebugLogger
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val imageURLFormatter = module {
    single { ImageLoaderConfig(BuildConfig.REMOTE_URL) }
    singleOf(::ConfigurationImageURLFormatter) bind ImageURLFormatter::class
    singleOf(::createImageLoader)
}

fun createImageLoader(
    context: Context,
): ImageLoader {
    return ImageLoader.Builder(context)
        .components {
            add(OkHttpNetworkFetcherFactory())
        }
        .apply {
            logger(DebugLogger())
        }
        .build()
}
