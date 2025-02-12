package jzy.taining.plugins.jspark.templates.prefSetting


import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.Constraint.NONEMPTY
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import com.android.tools.idea.wizard.template.impl.defaultPackageNameParameter
import java.io.File

val prefSettingsTemplate
    get() = template {
        name = "PrefSettings Activity"
        description = "Creates a new activity that allows a user to configure application settings"
        minApi = MIN_API

        category = Category.Activity
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewModule)

        val title = stringParameter {
            name = "业务界面标题"
            default = ""
            help = "用来生成Activity页面的标题"
            constraints = listOf(com.android.tools.idea.wizard.template.Constraint.STRING)
        }

        val business = stringParameter {
            name = "业务名"
            default = "Business"
            help = "用来生成Activity，ViewModel的业务名字"
            constraints = listOf(com.android.tools.idea.wizard.template.Constraint.STRING, NONEMPTY)
        }

        val beanGenerate = booleanParameter {
            name = "是否生成Bean"
            default = true
            help = "如果为true,会自动生成Bean文件"
        }

        val packageName = defaultPackageNameParameter

        widgets(
            TextFieldWidget(title),
            TextFieldWidget(business),
            CheckBoxWidget(beanGenerate),
            PackageNameWidget(packageName),
        )

        thumb { File("settings-activity").resolve("template_settings_activity.png") }

        recipe = { data: TemplateData ->
            prefSettingsRecipe(data as ModuleTemplateData, title.value, business.value, beanGenerate.value, packageName.value)
        }

    }