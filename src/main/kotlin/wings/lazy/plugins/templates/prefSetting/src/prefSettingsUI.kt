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
import com.gene.basic.ui.BasicPreferenceFragment
import com.gene.basic.ui.BasicStateActivity

class $activityName: BasicStateActivity<$viewModelName>() {

    override fun title() = $titleRes

    override fun showSucceedFragment() = $fragmentName()
}

class $fragmentName: BasicPreferenceFragment<$viewModelName>() {
    
    override fun PreferenceScreen.showScreen(data :$dtoName) {
        TODO("Not yet implemented")
    }
    
}
"""