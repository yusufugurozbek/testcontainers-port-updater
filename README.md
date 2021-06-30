# Testcontainers Port Updater

![Build](https://github.com/yusufugurozbek/testcontainers-port-updater/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/17116-testcontainers-port-updater.svg)](https://plugins.jetbrains.com/plugin/17116-testcontainers-port-updater)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/17116-testcontainers-port-updater.svg)](https://plugins.jetbrains.com/plugin/17116-testcontainers-port-updater)

<!-- Plugin description -->
You are using Testcontainers and want to have your Testcontainer database accessible directly within IntelliJ? You are tired of changing the port configuration all the time since Testcontainers chooses a random port on each startup? This plugin solves your issue.


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

For having a working plugin there are 2 requirements;

1- The plugin expects that Testcontainers writes a log that starts with ` Database: `. If you don't see any logs on your console, please check your Testcontainers configurations and your log level.

Example log; 
```
2021-06-23 14:07:26.408  INFO [your-service,,] 11111 --- [           main] o.f.c.i.database.base.DatabaseType       : Database: jdbc:postgresql://localhost:12345/test (PostgreSQL 10.13)
```

2- You need to add a new data source into your IntelliJ. For more details, please visit [this page](https://www.jetbrains.com/help/idea/connecting-to-a-database.html) from IntelliJ.

After covering these 2 requirements, the plugin automatically will catch the log and update the matched data source URL with the Testcontainers' one.

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
