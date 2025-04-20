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

class $activityName: StateActivity<$dtoName, $viewModelName>() {

    override fun title() = $titleRes
    
    /**
     * #### 数据加载成功之后要显示的fragment
     * - ***此方法在 [StateViewModel#doRequest()] 执行结束后执行***
     * - 子类必须实现，数据加载成功之后显示什么fragment
     *  - 已有3类实现了标题栏的基类Fragment
     *      - **PrefDslFragment，设置风格Fragment，通过PrefDsl构建页面不需要xml布局**
     *      - **ComposeFragment，使用compose绘制界面**
     *      - **ViewDslFragment，使用view绘制界面，通过ViesDsl构建页面不需要xml布局**
     */
    override fun showSuccessFragment() = $fragmentName()
}

class $fragmentName: ViewDslFragment<$dtoName>() {
    
    /**
     * ***此方法在 [StateViewModel#doRequest()] 执行结束后执行***
     * - 通过 Preference DSL 构建页面，不需要xml布局
     * - 通过 [data] 数据来自doRequest()返回的数据
     */
    override fun LinearLayout.onShowContent(data :$dtoName) {
        TODO("Not yet implemented")
    }
    
}
"""