package wings.lazy.plugins.templates.prefSetting.src

fun prefSettingsDTO(
    title: String,
    packageName: String,
    business: String
) = """
        
package ${packageName}.dto

/* $title 页面使用的数据模型 */
data class $business()


"""