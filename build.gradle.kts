buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:3.5.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")

        if (Modules.values().any {
                it.type.run {
                    contains(ProjectType.PROTOBUF) || contains(ProjectType.PROTOBUF_GRPC)
                }
            }) {
            classpath("com.google.protobuf:protobuf-gradle-plugin:${Versions.protobufScript}")
        }
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven("https://jitpack.io")
    }
}

subprojects {
    Modules.values().filter { it.path.equals(path, true) }.forEach {
        it.type.forEach { type ->
            when (type) {
                ProjectType.APPLICATION_ANDROID -> {
                    println("Applying Android application to $name")
                    apply {
                        from("$scriptsPath/android-app.gradle")
                        from("$scriptsPath/android-common.gradle")
                    }
                }
                ProjectType.LIBRARY_ANDROID -> {
                    println("Applying Android library to $name")
                    apply {
                        from("$scriptsPath/android-lib.gradle")
                        from("$scriptsPath/android-common.gradle")
                    }
                }
                ProjectType.LIBRARY_KOTLIN -> {
                    println("Applying Kotlin library to $name")
                    apply("$scriptsPath/kotlin-lib.gradle")
                }
                ProjectType.CPP_STATIC -> {
                    println("Applying CPP static library to $name")
                    apply("$scriptsPath/cpp-static.gradle")
                }
                ProjectType.CPP_SHARED -> {
                    println("Applying CPP shared library to $name")
                    apply("$scriptsPath/cpp-shared.gradle")
                }
                ProjectType.PROTOBUF -> {
                    println("Applying Protobuf library to $name")
                    apply("$scriptsPath/protobuf.gradle")
                }
                ProjectType.PROTOBUF_GRPC -> {
                    println("Applying Protobuf with GRPC library to $name")
                    apply("$scriptsPath/protobuf-grpc.gradle")
                }
            }
        }
    }

    apply("$scriptsPath/kt-lint.gradle")
}

task("clean", Delete::class) {
    delete(rootProject.buildDir)
}
