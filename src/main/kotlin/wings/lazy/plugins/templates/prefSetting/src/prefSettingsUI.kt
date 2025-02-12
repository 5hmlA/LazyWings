package wings.lazy.plugins.templates.prefSetting.src


fun prefSettingsUI(
    namespace: String,
    packageName: String,
    activityName: String,
    fragmentName: String,
    viewModelName: String,
    titleRes: String
) = """
        
package $packageName

import $namespace.R
import androidx.preference.PreferenceScreen
import com.gene.demo.basic.ui.BasicPreferenceFragment
import com.gene.demo.basic.ui.BasicStateWithDeviceActivity

class $activityName: BasicStateWithDeviceActivity<$viewModelName>() {

    override fun title() = $titleRes

    override fun showSucceedFragmentWithToolbar() = $fragmentName()
}

class $fragmentName: BasicPreferenceFragment<$viewModelName>() {
    
    override fun PreferenceScreen.showScreen() {
        TODO("Not yet implemented")
    }
    
}
"""