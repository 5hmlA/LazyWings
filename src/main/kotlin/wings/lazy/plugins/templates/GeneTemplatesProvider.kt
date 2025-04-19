package wings.lazy.plugins.templates

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import wings.lazy.plugins.templates.prefSetting.stateUITemplate

// https://www.sasikanth.dev/creating-project-templates-in-android-studio/
// https://cs.android.com/android-studio/platform/tools/base/+/mirror-goog-studio-main:wizard/template-impl/src/com/android/tools/idea/wizard/template/impl/

class GeneTemplatesProvider : WizardTemplateProvider() {

    override fun getTemplates(): List<Template> {
        return listOf(stateUITemplate)
    }

}