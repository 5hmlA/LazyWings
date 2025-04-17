package wings.lazy.plugins.templates.prefSetting.src

fun prefSettingsViewModelAny(
    packageName: String,
    viewModelName: String
) = """
package $packageName

import androidx.lifecycle.SavedStateHandle
import osp.spark.view.ui.StateViewModel


class $viewModelName(stateHandle: SavedStateHandle): StateViewModel<Any>(stateHandle) {
    
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
    
    override suspend fun doRequest(stateHandle: SavedStateHandle): ${fanxing}? {
        TODO("Not yet implemented")
    }
}

"""
}