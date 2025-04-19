package wings.lazy.plugins.templates.prefSetting.src

fun prefSettingsViewModelAny(
    packageName: String,
    viewModelName: String
) = """
package $packageName

import androidx.lifecycle.SavedStateHandle
import osp.spark.view.ui.StateViewModel


class $viewModelName(stateHandle: SavedStateHandle): StateViewModel<Any>(stateHandle) {
    
    /**
     * #### Activity一创建就会立刻执行此方法, Activity重建不会重复执行
     *  - 用于请求UI所需要的数据，可执行耗时操作
     *  - 方法结束后UI页面状态会从加载中状态切换到加载成功状态显示业务具体
     *  - 方法返回null会显示空页面
     *  - [stateHandle] 中可获取Intent内的参数
     *  - 此方法内抛出UIStateException时会显示对应UI状态，不抛出UIStateException会显示默认错误页面
     *      - 所有状态都定义在 **[osp.spark.view.ui.UIState]** 中.
     */
    override suspend fun doRequest(stateHandle: SavedStateHandle): Any? {
        TODO("Not yet implemented")
    }
}

"""

fun prefSettingsViewModel(
    packageName: String,
    viewModelName: String,
    dtoName: String,
    generateDTO: Boolean,
    isList: Boolean,
): String {
    val dtoNameImport = if (generateDTO) "import $packageName.dto.$dtoName" else ""
    val fanxing = if (isList) "List<$dtoName>" else dtoName
    return """
package $packageName

$dtoNameImport
import androidx.lifecycle.SavedStateHandle
import osp.spark.view.ui.StateViewModel


class $viewModelName(stateHandle: SavedStateHandle): StateViewModel<$fanxing>(stateHandle) {
    
    /**
     * #### Activity一创建就会立刻执行此方法, Activity重建不会重复执行
     *  - 用于请求UI所需要的数据，可执行耗时操作
     *  - 方法结束后UI页面状态会从加载中状态切换到加载成功状态显示业务具体
     *  - 方法返回null会显示空页面
     *  - [stateHandle] 中可获取Intent内的参数
     *  - 此方法内抛出UIStateException时会显示对应UI状态，不抛出UIStateException会显示默认错误页面
     *      - 所有状态都定义在 **[osp.spark.view.ui.UIState]** 中.
     */
    override suspend fun doRequest(stateHandle: SavedStateHandle): ${fanxing}? {
        TODO("Not yet implemented")
    }
}
"""
}