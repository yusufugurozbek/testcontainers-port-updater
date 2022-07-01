package com.github.yusufugurozbek.testcontainers.port.updater.settings

import com.github.yusufugurozbek.testcontainers.port.updater.common.TpuBundle
import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import javax.swing.JComponent

class TpuSettingsConfigurable : Configurable {
    private var settingsComponent: TpuSettingsComponent? = null

    override fun getDisplayName(): @Nls(capitalization = Nls.Capitalization.Title) String {
        return TpuBundle.message("name")
    }

    override fun getPreferredFocusedComponent(): JComponent {
        return settingsComponent!!.preferredFocusedComponent
    }

    override fun createComponent(): JComponent {
        settingsComponent = TpuSettingsComponent()
        return settingsComponent!!.panel
    }

    override fun isModified(): Boolean {
        val settings: TpuSettingsState = TpuSettingsState.instance
        return settingsComponent!!.isNotificationsEnabled != settings.isNotificationsEnabled
    }

    override fun apply() {
        val settings: TpuSettingsState = TpuSettingsState.instance
        settings.isNotificationsEnabled = settingsComponent!!.isNotificationsEnabled
    }

    override fun reset() {
        val settings: TpuSettingsState = TpuSettingsState.instance
        settingsComponent!!.isNotificationsEnabled = settings.isNotificationsEnabled
    }

    override fun disposeUIResources() {
        settingsComponent = null
    }
}
