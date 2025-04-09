package wings.lazy.plugins.templates.prefSetting.src

fun prefSettingsViewModelAny(
    packageName: String,
    viewModelName: String
) = """
package $packageName

import androidx.lifecycle.SavedStateHandle
import com.gene.demo.basic.BasicPrefViewModel


class $viewModelName(stateHandle: SavedStateHandle): BasicPrefViewModel<Any>(stateHandle) {
    
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
import com.gene.basic.BasicPrefViewModel


class $viewModelName(stateHandle: SavedStateHandle): BasicPrefViewModel<$fanxing>(stateHandle) {
    
    override suspend fun doRequest(stateHandle: SavedStateHandle): ${fanxing}? {
        TODO("Not yet implemented")
    }
}

"""
}