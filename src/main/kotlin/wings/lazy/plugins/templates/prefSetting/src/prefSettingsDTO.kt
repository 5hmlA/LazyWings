package wings.lazy.plugins.templates.prefSetting.src

fun prefSettingsDTO(
    title: String,
    packageName: String,
    business: String
) = """
        
package ${packageName}.dto

/* $title ҳ��ʹ�� */
data class $business()


"""