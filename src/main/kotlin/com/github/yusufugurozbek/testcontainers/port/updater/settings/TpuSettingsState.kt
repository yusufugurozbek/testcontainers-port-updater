package com.github.yusufugurozbek.testcontainers.port.updater.settings

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(
    name = "com.github.yusufugurozbek.testcontainers.port.updater.settings.TpuSettingsState",
    storages = [Storage("TestcontainersPortUpdaterPlugin.xml")]
)
@Service(Service.Level.PROJECT)
class TpuSettingsState : PersistentStateComponent<TpuSettingsState?> {
    var isNotificationsEnabled: Boolean = true
    var logEntryPrefix: String = "Database:"
        set(value) {
            if (value.isNotEmpty()) {
                field = value
            }
        }
    var matchMode: MatchMode = MatchMode.EXACT

    override fun getState(): TpuSettingsState {
        return this
    }

    override fun loadState(state: TpuSettingsState) {
        XmlSerializerUtil.copyBean(state, this)
    }
}
