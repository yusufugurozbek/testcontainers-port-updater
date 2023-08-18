package com.github.yusufugurozbek.testcontainers.port.updater.settings

import com.github.yusufugurozbek.testcontainers.port.updater.common.TpuBundle
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.util.Disposer
import com.intellij.ui.dsl.builder.bind
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel


class TpuSettingsComponent(private val tpuSettingsState: TpuSettingsState) {
    private val disposable = Disposer.newDisposable()

    val settingsPanel: DialogPanel = panel {
        row {
            checkBox(TpuBundle.message("settings.isNotificationsEnabledText")).bindSelected(tpuSettingsState::isNotificationsEnabled)
                .focused()
        }

        row(TpuBundle.message("settings.logEntryPrefixText")) {
            textField().bindText(tpuSettingsState::logEntryPrefix)
                .validationOnInput { if (it.text.isEmpty()) error(TpuBundle.message("settings.logEntryPrefixMustBeGiven")) else null }
        }

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
        }.bind(tpuSettingsState::matchMode)

        buttonsGroup(TpuBundle.message("settings.loggingFormat")) {
            row {
                radioButton(TpuBundle.message("settings.loggingFormatSimpleText"), LoggingFormat.SIMPLE_TEXT)
            }
            row {
                radioButton(TpuBundle.message("settings.loggingFormatJson"), LoggingFormat.JSON)
            }
        }.bind(tpuSettingsState::loggingFormat)

        group {
            row {
                text(TpuBundle.message("settings.giveAStar"))
            }
        }
    }.also { it.registerValidators(disposable) }
}
