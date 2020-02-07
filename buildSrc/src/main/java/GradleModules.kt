private const val scriptsFolder = "scripts"
val scriptsPath = "${System.getProperty("user.dir")}/$scriptsFolder"
val buildFilePath = "$scriptsFolder/main.gradle.kts"

enum class ProjectType {
    APPLICATION_ANDROID,
    LIBRARY_ANDROID,
    LIBRARY_KOTLIN,
    CPP_STATIC,
    CPP_SHARED,
    PROTOBUF,
    PROTOBUF_GRPC
}

enum class Modules(val path: String, val type: List<ProjectType>) {
    APP_EXAMPLE(":example:app", ProjectType.APPLICATION_ANDROID),
    CHARTS(":example:charts", ProjectType.LIBRARY_ANDROID),

    APP(":app", ProjectType.APPLICATION_ANDROID),
    BASE(":base", ProjectType.LIBRARY_ANDROID),
    CACHE(":cache", ProjectType.LIBRARY_ANDROID),
    NETWORK(":network", ProjectType.LIBRARY_ANDROID);

    constructor(path: String, type: ProjectType) : this(path, listOf(type))
}
