# Testcontainers Port Updater

[![Release](https://github.com/yusufugurozbek/testcontainers-port-updater/actions/workflows/release.yml/badge.svg)](https://github.com/yusufugurozbek/testcontainers-port-updater/actions/workflows/release.yml)
[![Version](https://img.shields.io/jetbrains/plugin/v/17116-testcontainers-port-updater.svg)](https://plugins.jetbrains.com/plugin/17116-testcontainers-port-updater)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/17116-testcontainers-port-updater.svg)](https://plugins.jetbrains.com/plugin/17116-testcontainers-port-updater)

<!-- Plugin description -->
You are using Testcontainers and want to have your Testcontainers database accessible directly within IntelliJ? You are tired of changing the port configuration all the time since Testcontainers chooses a random port on each startup? This plugin solves your issue.


It automatically updates the IntelliJ data source port number with the exposed random port number that Testcontainers chose on startup.

If you don't know what is Testcontainers, please check their official web page https://www.testcontainers.org/
<!-- Plugin description end -->

## Installation

- Using IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "Testcontainers Port Updater"</kbd> >
  <kbd>Install Plugin</kbd>
  
- Manually:

  Download the [latest release](https://github.com/yusufugurozbek/testcontainers-port-updater/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

## Usage

For having a working plugin, there are 2 requirements;

1- The plugin expects that your application writes a log that starts with the log entry prefix (default log entry prefix is `Database:`).
If you don't see any logs on your console, please check your configurations and your log level.

Example log; 
```
2021-06-23 14:07:26.408  INFO [your-service,,] 11111 --- [           main] o.f.c.i.database.base.DatabaseType       : Database: jdbc:postgresql://localhost:12345/test (PostgreSQL 10.13)
```

2- Copy the URL (`jdbc:postgresql://localhost:12345/test`) from the console log and add it to your IntelliJ as a new data source. 
For more details, please visit [this page](https://www.jetbrains.com/help/idea/connecting-to-a-database.html) from IntelliJ.

After covering these 2 requirements, the plugin automatically will catch the log and update the matched data source URL with the Testcontainers' one.

### Plugin Settings

To adjust Testcontainers Port Updater plugin settings, open IntelliJ preferences and navigate to **Tools | Testcontainers Port Updater**.
Please be aware, settings are stored at the project level, so each project has its own settings.

#### - `Enable notifications` Enable/disable 'Updated data source URL' notifications
#### - `Enter log entry prefix` Default is `Database:`
#### - `Select match mode`
  - `Exact match`<br>
    Logged data source URL and IntelliJ data source URL should be the same ignoring port part. Example match will be:<br>
    <b>Logged data source URL:</b> `Database: jdbc:postgresql://localhost:11111/test?loggerLevel=OFF`<br>
    <b>IntelliJ data source URL:</b> `jdbc:postgresql://localhost:22222/test?loggerLevel=OFF`<br><br>
  - `Everything`<br>
    Before the port part (`jdbc:postgresql://localhost`) should be the same for the logged data source URL and IntelliJ data source URL. Example match will be:<br>
    <b>Logged data source URL:</b> `Database: jdbc:postgresql://localhost:11111/test`<br>
    <b>IntelliJ data source URL:</b> `jdbc:postgresql://localhost:22222/test?loggerLevel=OFF`<br><br>
  - `With testcontainers=true parameter`<br>
    IntelliJ data source URL should contain `testcontainers=true` and before the port part (`jdbc:postgresql://localhost`) should be the same\
    for the logged data source URL and IntelliJ data source URL. Example match will be:<br>
    <b>Logged data source URL:</b> `Database: jdbc:postgresql://localhost:11111/test?loggerLevel=OFF`<br>
    <b>IntelliJ data source URL:</b> `jdbc:postgresql://localhost:22222/test?testcontainers=true`
---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
