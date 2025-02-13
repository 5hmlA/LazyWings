package wings.lazy.plugins.templates.prefSetting.res.values


fun stringsXml(
    title: String,
    titleResName: String) =
    """<resources>
    <!-- $title Ò³Ãæ±êÌâ -->
    <string name="$titleResName">${title}</string>
</resources>
"""