package jzy.taining.plugins.jspark.features.templates

import jzy.taining.plugins.jspark.features.templates.data.Environment
import jzy.taining.plugins.jspark.features.templates.data.TempConfig

interface TemplateFileFactory {
    fun doCreate(environment: Environment, tempConfig: TempConfig)
}