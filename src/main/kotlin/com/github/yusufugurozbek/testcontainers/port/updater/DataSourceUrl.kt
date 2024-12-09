package com.github.yusufugurozbek.testcontainers.port.updater

import com.github.yusufugurozbek.testcontainers.port.updater.settings.MatchMode
import com.github.yusufugurozbek.testcontainers.port.updater.settings.MatchMode.*
import com.intellij.database.dataSource.LocalDataSource
import com.intellij.database.util.common.isNotNullOrEmpty

data class DataSourceUrl(val beforePort: String, val port: String, val afterPort: String?, private val urlExtractor : DataSourceUrlExtractor) {

    constructor(beforePort: String, port: String, afterPort: String?) : this(beforePort, port, afterPort, DataSourceUrlExtractor())

    init {
        setUrlExtractor(urlExtractor)
    }

    companion object {
        private var extractor: DataSourceUrlExtractor = DataSourceUrlExtractor()

        fun setUrlExtractor(extractor: DataSourceUrlExtractor) {
            // Initialize extractor when first needed

                this.extractor = extractor

        }

        fun from(dataSource: LocalDataSource): DataSourceUrl? = from(dataSource.url)

        fun from(url: String?): DataSourceUrl? {
            return url?.takeIf { it.isNotNullOrEmpty }
                ?.let { extractor.extract(it)?.let(::toDataSourceUrl) }
        }

        private fun toDataSourceUrl(matchResult: MatchResult?): DataSourceUrl? {
            val groups = matchResult?.groups
            val beforePort = groups?.get("beforePort")?.value
            val port = groups?.get("port")?.value
            val afterPort = groups?.get("afterPort")?.value

            return if (beforePort != null && port != null) {
                DataSourceUrl(beforePort, port, afterPort)
            } else {
                null
            }
        }
    }

    fun matches(other: DataSourceUrl, matchMode: MatchMode): Boolean =
        when (matchMode) {
            EXACT -> this.equalsIgnoringPort(other)
            EVERYTHING -> this.beforePort == other.beforePort
            WITH_TESTCONTAINERS_PARAMETER ->
                this.toString().contains("testcontainers=true") and
                        (this.beforePort == other.beforePort)
        }

    private fun equalsIgnoringPort(other: DataSourceUrl): Boolean =
        (this.beforePort == other.beforePort) and (this.afterPort == other.afterPort)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DataSourceUrl) return false

        return beforePort == other.beforePort &&
                port == other.port &&
                afterPort == other.afterPort
    }

    override fun hashCode(): Int {
        return 31 * beforePort.hashCode() + port.hashCode() + (afterPort?.hashCode() ?: 0)
    }

    override fun toString(): String = "$beforePort:$port$afterPort"
}
