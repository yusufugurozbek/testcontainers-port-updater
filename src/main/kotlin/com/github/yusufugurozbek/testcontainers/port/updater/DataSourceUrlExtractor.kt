package com.github.yusufugurozbek.testcontainers.port.updater

class DataSourceUrlExtractor {

    companion object {
        private val regex: Regex = "\\s*(?<beforePort>.*):(?<port>\\d{1,5})(?<afterPort>.*;\\S*|\\S*)".toRegex()
    }

    fun extract(from: String?): MatchResult? = from?.let { regex.find(from) }

}
