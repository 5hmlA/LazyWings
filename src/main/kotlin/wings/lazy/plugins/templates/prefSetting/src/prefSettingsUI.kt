package wings.lazy.plugins.templates.prefSetting.src


fun prefSettingsUI(
    namespace: String,
    packageName: String,
    activityName: String,
    fragmentName: String,
    viewModelName: String,
    dtoName: String,
    titleRes: String
) = """
        
package $packageName

import $namespace.R
import androidx.preference.PreferenceScreen
import osp.spark.view.ui.ViewDslFragment
import osp.spark.view.ui.StateActivity

class $activityName: StateActivity<$viewModelName>() {

    override fun title() = $titleRes

    override fun showSuccessFragment() = $fragmentName()
}

class $fragmentName: ViewDslFragment<$viewModelName>() {
    
    override fun LinearLayout.onShowContent(data :$dtoName) {
        TODO("Not yet implemented")
    }
    
}
"""