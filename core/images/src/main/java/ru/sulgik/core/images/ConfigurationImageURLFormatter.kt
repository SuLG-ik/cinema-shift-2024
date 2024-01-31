package ru.sulgik.core.images

internal class ConfigurationImageURLFormatter(
    private val config: ImageLoaderConfig,
) : ImageURLFormatter {

    override fun format(url: String): String {
        return "$config$url"
    }

}