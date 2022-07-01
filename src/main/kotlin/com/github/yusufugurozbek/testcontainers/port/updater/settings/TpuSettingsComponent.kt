package com.github.yusufugurozbek.testcontainers.port.updater.settings

import com.github.yusufugurozbek.testcontainers.port.updater.common.TpuBundle
import com.intellij.ui.components.JBCheckBox
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JPanel

class TpuSettingsComponent {
    val panel: JPanel
    private val isNotificationsEnabledCheckbox = JBCheckBox(TpuBundle.message("settings.isNotificationsEnabledText"))

    init {
        panel = FormBuilder.createFormBuilder()
            .addComponent(isNotificationsEnabledCheckbox, 1)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

    val preferredFocusedComponent: JComponent
        get() = isNotificationsEnabledCheckbox
    var isNotificationsEnabled: Boolean
        get() = isNotificationsEnabledCheckbox.isSelected
        set(value) {
            isNotificationsEnabledCheckbox.isSelected = value
        }
}
