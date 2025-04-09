package wings.lazy.plugins.templates.prefSetting

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.Constraint.NONEMPTY
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import com.android.tools.idea.wizard.template.impl.defaultPackageNameParameter
import java.io.File

val prefSettingsTemplate
    get() = template {
        name = "PrefSettings Activity"
        description = "一键生成卡片风格设置页面"
        minApi = MIN_API
        //定义模版显示在哪
        category = Category.UiComponent
        formFactor = FormFactor.Mobile
//        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.FragmentGallery, WizardUiContext.NewModule)
        screens = listOf(WizardUiContext.MenuEntry)

        val business = stringParameter {
            name = "业务类"
            default = "Business"
            help = "用来生成Activity，ViewModel的业务名字"
            constraints = listOf(Constraint.STRING, NONEMPTY)
        }

        val title = stringParameter {
            name = "标题"
            default = ""
            help = "用来生成Activity页面的标题"
            constraints = listOf(Constraint.STRING)
        }

        val dtoGenerate = booleanParameter {
            name = "是否生成DTO文件"
            default = true
            help = "如果为true,会自动生成Bean文件"
        }
        val isListDto = booleanParameter {
            name = "页面是否需要List"
            default = true
            help = "如果为true,使用List"
        }

        val dto = stringParameter {
            name = "DTO"
            default = ""
            suggest = { business.value }
//            enabled = { dtoGenerate.value }
            constraints = listOf(Constraint.STRING)
        }

        val businessPackage = booleanParameter {
            name = "独立到业务包"
            default = false
            enabled = { false }
            help = "如果为true,所有生成的文件会在单独包下"
        }

        val kotlinSrcDir = booleanParameter {
            name = "kotlinSrcDir"
            default = false
            help = "代码生成到kotlin包下，默认生成到java包下"
        }

        val packageName = defaultPackageNameParameter

        widgets(
            TextFieldWidget(business),
            TextFieldWidget(title),
            TextFieldWidget(dto),
            CheckBoxWidget(isListDto),
            CheckBoxWidget(dtoGenerate),
            CheckBoxWidget(businessPackage),
            PackageNameWidget(packageName),
            CheckBoxWidget(kotlinSrcDir),
        )

//        thumb { File("settings-activity").resolve("template_settings_activity.png") }
        thumb {
            File("no_activity.png")
        }

        recipe = { data: TemplateData ->
            val dtoValue: String = if (dto.value.isEmpty()) {
                "Any"
            } else {
                dto.value
            }
            val packageNameValue = if (businessPackage.value) {
                packageName.value + ".${business.value.toLowerCase()}"
            } else {
                packageName.value
            }
            prefSettingsRecipe(
                data as ModuleTemplateData,
                title.value, business.value,
                dtoGenerate.value, dtoValue, isListDto.value,
                packageNameValue, kotlinSrcDir.value
            )
        }

    }