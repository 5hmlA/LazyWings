package wings.lazy.plugins.templates.prefSetting

fun androidManifestXml(
    packageName: String,
    activityName: String,
) = """
<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <application>
        <activity android:name="${packageName}.${activityName}"
            android:launchMode="singleTop"
            android:screenOrientation="locked" 
            android:exported="true" />
    </application>

</manifest>
"""