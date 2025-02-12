package wings.lazy.plugins.templates

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import wings.lazy.plugins.templates.prefSetting.prefSettingsTemplate

class GeneTemplatesProvider : WizardTemplateProvider() {

    override fun getTemplates(): List<Template> {
        return listOf(prefSettingsTemplate)
    }
}