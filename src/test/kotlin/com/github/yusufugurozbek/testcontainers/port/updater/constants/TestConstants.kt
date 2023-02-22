package com.github.yusufugurozbek.testcontainers.port.updater.constants

object TestConstants {
    const val WITHOUT_PORT_LOG_ENTRY = "2022-10-05 19:19:06.886  INFO 16604 --- [" +
            "           main] o.f.c.i.database.base.BaseDatabaseType   : " +
            "Database: jdbc:mysql://localhost/test (MySQL 8.0)"
    const val DUMMY_LOG_ENTRY = "2022-10-05 19:19:06.886  INFO 16604 --- [" +
            "           main] o.f.c.i.database.base.BaseDatabaseType   : Database: someGibberish"
    const val DUMMY_LOG_ENTRY_WITHOUT_PREFIX = "2022-10-05 19:19:06.886  INFO 16604 --- [" +
            "           main] o.f.c.i.database.base.BaseDatabaseType   : someGibberish"
}
