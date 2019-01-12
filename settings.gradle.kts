pluginManagement {
    repositories {
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {

            // If no version specified, try finding one
            // from the gradle.properties
            if (requested.version.isNullOrEmpty()) {
                val key = "${requested.id.id}-version"
                if (gradle.rootProject.extra.has(key)) {
                    gradle.rootProject.extra.get(key)
                        ?.let {
                            useVersion(it.toString())
                        }
                }
            }
        }
    }
}