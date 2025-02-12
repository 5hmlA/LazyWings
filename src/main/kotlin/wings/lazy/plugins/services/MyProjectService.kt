package wings.lazy.plugins.services

import com.intellij.openapi.project.Project
import wings.lazy.plugins.jspark.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }


}
