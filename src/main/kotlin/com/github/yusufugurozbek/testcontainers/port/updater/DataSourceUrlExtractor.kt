package com.github.yusufugurozbek.testcontainers.port.updater

class DataSourceUrlExtractor {

    private val regex: Regex = "Database: ((.*?)((?!\\S)(\\s.*\\w.*;|)))".toRegex()

    internal fun extract(from: String): String? {
        val find = regex.find(from)
        return find?.groupValues?.get(1)
    }
}
