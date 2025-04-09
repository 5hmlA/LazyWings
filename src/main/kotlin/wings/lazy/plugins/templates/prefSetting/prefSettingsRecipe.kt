package wings.lazy.plugins.templates.prefSetting


import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.activityToLayout
import wings.lazy.plugins.templates.prefSetting.res.values.stringsXml
import wings.lazy.plugins.templates.prefSetting.src.prefSettingsDTO
import wings.lazy.plugins.templates.prefSetting.src.prefSettingsUI
import wings.lazy.plugins.templates.prefSetting.src.prefSettingsViewModel
import java.io.File

private fun File.toKotlinSrcDir(kotlinSrcDir: Boolean) = if (kotlinSrcDir) {
    File(absolutePath.replace("${File.separator}java${File.separator}", "${File.separator}kotlin${File.separator}"))
} else {
    this
}


fun RecipeExecutor.prefSettingsRecipe(
    moduleData: ModuleTemplateData,
    title: String,
    business: String,
    beanGenerate: Boolean,
    dto: String,
    isList: Boolean,
    packageName: String,
    kotlinSrcDir: Boolean,
) {
//    packageName 鼠标所选中的包名
    val (projectData, javaSrcOut, resOut, manifestOut) = moduleData
    val srcOut = javaSrcOut.toKotlinSrcDir(kotlinSrcDir)
    val activityClass = "${business}Activity"
    val fragmentClass = "${business}Fragment"
    val viewModelClass = "${business}ViewModel"
    val simpleName = activityToLayout(activityClass)
//    //    NetWorkActivity -> activity_net_work
//    println(activityToLayout(activityClass))
//    //    NetWorkActivity -> net_work_activity
//    println(camelCaseToUnderlines(activityClass))
//    //    NetWorkActivity -> net_work
//    println(classToResource(activityClass))

    val uiPage = if (title.isNotEmpty()) title else activityClass

    val titleRes = if (title.isNotEmpty()) {
        val titleResName = "title_${simpleName}"
        mergeXml(stringsXml(title, titleResName), resOut.resolve("values/strings.xml"))
        mergeXml(stringsXml(title, titleResName), resOut.resolve("values-zh-rCN/strings.xml"))
        "R.string.$titleResName"
    } else "0"

    //生成源码
    val uiSrcOut = srcOut.resolve("${business}UI.kt")
    save(
        prefSettingsUI(
            projectData.applicationPackage ?: packageName,
            packageName,
            activityClass,
            fragmentClass,
            viewModelClass,
            titleRes
        ), uiSrcOut
    )
    open(uiSrcOut)
    if (beanGenerate && !dto.contains("Any")) {
        val split = dto.split(".")
        if (split.size > 1) {
            //dtoBusiness.Order 把所有dto添加到一个文件下
            val dtoSrcOut = srcOut.resolve("dto/${split.first()}.kt")
            val dto = split.last()
            append(prefSettingsDTO(uiPage, packageName, dto), dtoSrcOut)
            //append 可以创建可以追加
            open(dtoSrcOut)

            val viewModelSrcOut = srcOut.resolve("${viewModelClass}.kt")
            save(
                prefSettingsViewModel(
                    packageName,
                    viewModelClass, dto,
                    true, isList
                ), viewModelSrcOut
            )
            open(viewModelSrcOut)
        } else {
            val dtoSrcOut = srcOut.resolve("dto/${dto}.kt")
            save(prefSettingsDTO(uiPage, packageName, dto), dtoSrcOut)
            open(dtoSrcOut)

            val viewModelSrcOut = srcOut.resolve("${viewModelClass}.kt")
            save(prefSettingsViewModel(
                packageName,
                viewModelClass, dto,
                true, isList
            ), viewModelSrcOut)
            open(viewModelSrcOut)
        }
    } else {
        val viewModelSrcOut = srcOut.resolve("${viewModelClass}.kt")
        save(prefSettingsViewModel(
            packageName,
            viewModelClass, dto,
            false, isList
        ), viewModelSrcOut)
        open(viewModelSrcOut)
    }

    //生成DTO，或者追加到dto
//    append(prefSettingsDTO(packageName, business), dtoSrcOut)

    //把具体内容生成或者合并到某个xml文件
    //mergeXml(settingsActivityXml(), resOut.resolve("layout/settings_activity.xml"))
//    mergeXml(stringsXml(activityClass, simpleName), resOut.resolve("values/strings.xml"))
//    mergeXml(arraysXml(), resOut.resolve("values/arrays.xml"))

    //添加Activity到Manifest
//    generateManifest(
//        moduleData, activityClass, packageName, isLauncher = false, hasNoActionBar = false,
//        generateActivityTitle = false
//    )
    mergeXml(androidManifestXml(packageName, activityClass), manifestOut.resolve("AndroidManifest.xml"))


//    val useAndroidX = moduleData.projectTemplateData.androidXSupport
//    val ktOrJavaExt = projectData.language.extension
//    addAllKotlinDependencies(moduleData)
//
//    addDependency("com.android.support:appcompat-v7:${moduleData.apis.appCompatVersion}.+")
//    addDependency("androidx.preference:preference:+")
//    addMaterialDependency(useAndroidX)
}