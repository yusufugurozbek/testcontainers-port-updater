package com.github.yusufugurozbek.testcontainers.port.updater

import com.github.yusufugurozbek.testcontainers.port.updater.settings.MatchMode
import com.github.yusufugurozbek.testcontainers.port.updater.settings.MatchMode.*
import com.intellij.database.dataSource.LocalDataSource
import com.intellij.database.util.common.isNotNullOrEmpty

data class DataSourceUrl(val beforePort: String, val port: String, val afterPort: String?) {


    companion object {
        private var dataSourceUrlExtractor: DataSourceUrlExtractor = DataSourceUrlExtractor()

        fun from(dataSource: LocalDataSource): DataSourceUrl? = from(dataSource.url)

        fun from(url: String?): DataSourceUrl? {
            return url?.takeIf { it.isNotNullOrEmpty }
                ?.let { dataSourceUrlExtractor.extract(it)?.let(::toDataSourceUrl) }
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

    override fun toString(): String = "$beforePort:$port$afterPort"
}
