package wings.lazy.plugins.templates.prefSetting


import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.activityToLayout
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import wings.lazy.plugins.templates.prefSetting.res.values.stringsXml
import wings.lazy.plugins.templates.prefSetting.src.prefSettingsDTO
import wings.lazy.plugins.templates.prefSetting.src.prefSettingsUI
import wings.lazy.plugins.templates.prefSetting.src.prefSettingsViewModel

fun RecipeExecutor.prefSettingsRecipe(
    moduleData: ModuleTemplateData,
    title: String,
    business: String,
    beanGenerate: Boolean,
    packageName: String
) {
//    packageName 鼠标所选中的包名
    val (projectData, srcOut, resOut, _) = moduleData

    val activityClass = "${business}Activity"
    val fragmentClass = "${business}Fragment"
    val viewModelClass = "${business}ViewModel"
//    activityToLayout
//    camelCaseToUnderlines
//    classToResource
//    extractClassName
    val simpleName = activityToLayout(activityClass)

    val titleRes = if (title.isNotEmpty()) {
        val titleResName = "title_${simpleName}"
        mergeXml(stringsXml(title, titleResName), resOut.resolve("values/strings.xml"))
        mergeXml(stringsXml(title, titleResName), resOut.resolve("values-zh-rCN/strings.xml"))
        "R.string.$titleResName"
    } else "0"

    //生成源码
    val uiSrcOut = srcOut.resolve("${business}UI.kt")
    save(prefSettingsUI(projectData.applicationPackage!!, packageName, activityClass, fragmentClass, viewModelClass, titleRes), uiSrcOut)
    open(uiSrcOut)
    val viewModelSrcOut = srcOut.resolve("${viewModelClass}.kt")
    save(prefSettingsViewModel(packageName, activityClass, business), viewModelSrcOut)
    open(viewModelSrcOut)
    val dtoSrcOut = srcOut.resolve("dto/${business}.kt")
    save(prefSettingsDTO(packageName, business), dtoSrcOut)
    open(dtoSrcOut)

    //把具体内容生成或者合并到某个xml文件
    //mergeXml(settingsActivityXml(), resOut.resolve("layout/settings_activity.xml"))
//    mergeXml(stringsXml(activityClass, simpleName), resOut.resolve("values/strings.xml"))
//    mergeXml(arraysXml(), resOut.resolve("values/arrays.xml"))

    //添加Activity到Manifest
    generateManifest(
        moduleData, activityClass, packageName, isLauncher = false, hasNoActionBar = false,
        generateActivityTitle = false
    )

//    val useAndroidX = moduleData.projectTemplateData.androidXSupport
//    val ktOrJavaExt = projectData.language.extension
//    addAllKotlinDependencies(moduleData)
//
//    addDependency("com.android.support:appcompat-v7:${moduleData.apis.appCompatVersion}.+")
//    addDependency("androidx.preference:preference:+")
//    addMaterialDependency(useAndroidX)
}