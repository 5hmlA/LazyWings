package jzy.taining.plugins.jspark.templates

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import jzy.taining.plugins.jspark.templates.prefSetting.prefSettingsTemplate

class SparkTemplatesProvider : WizardTemplateProvider() {

    override fun getTemplates(): List<Template> {
        return listOf(prefSettingsTemplate)
    }
}