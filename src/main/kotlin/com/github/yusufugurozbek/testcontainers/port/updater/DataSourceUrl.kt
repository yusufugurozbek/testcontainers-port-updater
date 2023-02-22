package com.github.yusufugurozbek.testcontainers.port.updater

data class DataSourceUrl(val beforePort: String, val port: String, val afterPort: String?) {
    override fun toString(): String = "$beforePort:$port$afterPort"
}

fun DataSourceUrl.equalsIgnoringPort(other: DataSourceUrl): Boolean =
    (this.beforePort == other.beforePort) and (this.afterPort == other.afterPort)
