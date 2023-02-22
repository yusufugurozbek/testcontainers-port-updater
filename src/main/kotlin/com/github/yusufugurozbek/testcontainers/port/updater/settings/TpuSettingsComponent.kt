package com.github.yusufugurozbek.testcontainers.port.updater.settings

import com.github.yusufugurozbek.testcontainers.port.updater.common.TpuBundle
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.util.Disposer
import com.intellij.ui.dsl.builder.bind
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.dsl.gridLayout.HorizontalAlign
import com.intellij.ui.dsl.gridLayout.VerticalAlign


class TpuSettingsComponent {
    private val disposable = Disposer.newDisposable()

    val settingsPanel: DialogPanel = panel {
        row {
            checkBox(TpuBundle.message("settings.isNotificationsEnabledText")).bindSelected(TpuSettingsState.instance::isNotificationsEnabled)
                .focused()
        }

        separator()

        row(TpuBundle.message("settings.logEntryPrefixText")) {
            textField().bindText(TpuSettingsState.instance::logEntryPrefix)
                .validationOnInput { if (it.text.isEmpty()) error(TpuBundle.message("settings.logEntryPrefixMustBeGiven")) else null }
        }

        separator()

        buttonsGroup(TpuBundle.message("settings.matchModeText")) {
            row {
                text(TpuBundle.message("settings.matchModeMoreDetailsText"))
            }
            row {
                radioButton(TpuBundle.message("settings.matchModeExactRadioButtonText"), MatchMode.EXACT)
            }
            row {
                radioButton(TpuBundle.message("settings.matchModeEverythingRadioButtonText"), MatchMode.EVERYTHING)
            }
            row {
                radioButton(
                    TpuBundle.message("settings.matchModeWithTcParameterRadioButtonText"),
                    MatchMode.WITH_TESTCONTAINERS_PARAMETER
                )
            }
        }.bind(TpuSettingsState.instance::matchMode)

        separator()

        row {
            text(TpuBundle.message("settings.giveAStar"))
                .verticalAlign(VerticalAlign.BOTTOM)
                .horizontalAlign(HorizontalAlign.RIGHT)
        }
    }.also { it.registerValidators(disposable) }
}
