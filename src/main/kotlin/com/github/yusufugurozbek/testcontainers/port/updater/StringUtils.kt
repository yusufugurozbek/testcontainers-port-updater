package com.github.yusufugurozbek.testcontainers.port.updater

private val PORT_REGEX = ":\\d+".toRegex()

fun String.equalsIgnoringPort(other: String): Boolean {
    val thisWithoutNumbers = this.replace(PORT_REGEX, "")
    val otherWithoutNumbers = other.replace(PORT_REGEX, "")

    return thisWithoutNumbers == otherWithoutNumbers
}
