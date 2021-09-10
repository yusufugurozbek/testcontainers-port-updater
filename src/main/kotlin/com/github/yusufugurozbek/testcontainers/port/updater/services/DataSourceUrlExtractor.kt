package com.github.yusufugurozbek.testcontainers.port.updater.services

import com.github.yusufugurozbek.testcontainers.port.updater.TpuBundle

class DataSourceUrlExtractor {

   private val regexPattern = TpuBundle.message("logEntryRegex")
   private val regex: Regex = regexPattern.toRegex()

   internal fun extractDataSourceUrl(text: String): String? {
      val find = regex.find(text)
      return find?.groupValues?.get(1)
   }
}
