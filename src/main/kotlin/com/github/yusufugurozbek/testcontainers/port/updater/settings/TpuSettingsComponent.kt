package com.github.yusufugurozbek.testcontainers.port.updater.settings

import com.github.yusufugurozbek.testcontainers.port.updater.common.TpuBundle
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.panel


class TpuSettingsComponent {
    val settingsPanel: DialogPanel = panel {
        row {
            checkBox(TpuBundle.message("settings.isNotificationsEnabledText")).bindSelected(TpuSettingsState.instance::isNotificationsEnabled)
                .focused()
        }
    }
}
