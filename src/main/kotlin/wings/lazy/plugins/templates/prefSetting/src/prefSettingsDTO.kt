package wings.lazy.plugins.templates.prefSetting.src

fun prefSettingsDTO(
    packageName: String,
    business: String
) = """
        
package ${packageName}.dto

data class $business()

"""