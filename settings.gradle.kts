// Does not work with Android Studio :(
//rootProject.buildFileName = buildFilePath
Modules.values().forEach { include(it.path) }
