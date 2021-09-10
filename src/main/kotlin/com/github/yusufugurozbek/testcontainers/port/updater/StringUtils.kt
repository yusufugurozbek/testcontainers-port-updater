package com.github.yusufugurozbek.testcontainers.port.updater

private val NUMBER_REGEX = "\\d".toRegex()

fun String.equalsIgnoringPort(other: String): Boolean {
    val thisWithoutNumbers = this.replace(NUMBER_REGEX, "")
    val otherWithoutNumbers = other.replace(NUMBER_REGEX, "")

    return thisWithoutNumbers == otherWithoutNumbers
}
