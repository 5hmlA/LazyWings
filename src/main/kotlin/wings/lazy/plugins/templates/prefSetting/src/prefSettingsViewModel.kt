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
    dtoName: String
) = """
package $packageName

import $packageName.dto.$dtoName
import androidx.lifecycle.SavedStateHandle
import com.gene.demo.basic.BasicPrefViewModel


class $viewModelName(stateHandle: SavedStateHandle): BasicPrefViewModel<$dtoName>(stateHandle) {
    
    override suspend fun doRequest(stateHandle: SavedStateHandle): ${dtoName}? {
        TODO("Not yet implemented")
    }
}

"""