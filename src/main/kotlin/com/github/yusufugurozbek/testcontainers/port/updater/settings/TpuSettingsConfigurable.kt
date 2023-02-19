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

    override fun createComponent(): JComponent {
        settingsComponent = TpuSettingsComponent()
        return settingsComponent!!.settingsPanel
    }

    override fun isModified(): Boolean {
        return settingsComponent!!.settingsPanel.isModified()
    }

    override fun apply() {
        settingsComponent!!.settingsPanel.apply()
    }

    override fun reset() {
        settingsComponent!!.settingsPanel.reset()
    }

    override fun disposeUIResources() {
        settingsComponent = null
    }
}
