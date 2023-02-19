package com.github.yusufugurozbek.testcontainers.port.updater.settings

import com.github.yusufugurozbek.testcontainers.port.updater.common.TpuBundle
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.dsl.gridLayout.HorizontalAlign
import com.intellij.ui.dsl.gridLayout.VerticalAlign


class TpuSettingsComponent {
    val settingsPanel: DialogPanel = panel {
        row {
            checkBox(TpuBundle.message("settings.isNotificationsEnabledText")).bindSelected(TpuSettingsState.instance::isNotificationsEnabled)
                .focused()
        }

        separator()

        row {
            text(TpuBundle.message("settings.giveAStar"))
                .verticalAlign(VerticalAlign.BOTTOM)
                .horizontalAlign(HorizontalAlign.RIGHT)
        }
    }
}
