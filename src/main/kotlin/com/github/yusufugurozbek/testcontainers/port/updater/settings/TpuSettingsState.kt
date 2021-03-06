package com.github.yusufugurozbek.testcontainers.port.updater.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(
    name = "com.github.yusufugurozbek.testcontainers.port.updater.settings.AppSettingsState",
    storages = [Storage("TestcontainersPortUpdaterPlugin.xml")]
)
class TpuSettingsState : PersistentStateComponent<TpuSettingsState?> {
    var isNotificationsEnabled = true

    override fun getState(): TpuSettingsState {
        return this
    }

    override fun loadState(state: TpuSettingsState) {
        XmlSerializerUtil.copyBean(state, this)
    }

    companion object {
        val instance: TpuSettingsState
            get() = ApplicationManager.getApplication().getService(TpuSettingsState::class.java)
    }
}
