package jzy.taining.plugins.jspark.templates.prefSetting.res.values


fun stringsXml(
    title: String,
    titleResName: String) =
    """<resources>
    <!-- Activity Title -->
    <string name="$titleResName">${title}</string>
</resources>
"""