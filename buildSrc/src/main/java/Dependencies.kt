@file:Suppress("unused", "MayBeConstant")

object Versions {
    val kotlin = "1.3.50"
    val protobufScript = "0.8.10"

    val compileSdk = 29
    val buildTools = "29.0.2"
    val minSdk = 26
    val targetSdk = 26

    val slidingUpPanel = "3.4.0"
    val sparkGraph = "1.2.0"

    val googleAuth = "16.0.1"
    val playCore = "1.6.1"

    val fireBaseAuth = "16.0.4"
    val fireBaseCore = "16.0.4"

    val coRoutines = "1.2.1"

    val ktx = "1.1.0"
    val multiDex = "2.0.1"
    val room = "2.2.1"

    val koin = "2.0.1"

    val rxJava = "2.2.15"
    val rxKotlin = "2.3.0"
    val rxAndroid = "2.1.1"
    val rxJavaExtensions = "0.20.10"

    val paperdb = "2.6"
    val rxpaper = "1.4.0"

    val retrofit = "2.6.2"
    val coRoutineAdapter = "0.9.2"
    val loggingInterceptor = "4.0.0"

    val glide = "4.9.0"

    val appcompat = "1.0.2"
    val design = "1.0.0"
    val cardview = "1.0.0"
    val recyclerView = "1.0.0"
    val constraintLayout = "2.0.0-beta3"
    val lifecycle = "2.1.0"

    val protobuf = "3.9.1"
    val grpc = "1.23.0"
    val javaXAnnotations = "1.3.2"

    val junit = "4.12"
    val kotlinTest = "3.4.0"
    val testRunner = "1.1.1"
    val espresso = "3.1.1"
    val assertjCore = "3.12.2"
    val mockitoKotlin = "2.1.0"
    val mockitoAndroid = "3.2.0"
}

object LibOther {
    val slidingUpPanel = "com.sothree.slidinguppanel:library:${Versions.slidingUpPanel}"
    val sparkGraph = "com.robinhood.spark:spark:${Versions.sparkGraph}"
}

object LibGoogle {
    val auth = "com.google.android.gms:play-services-auth:${Versions.googleAuth}"
    val playCore = "com.google.android.play:core:${Versions.playCore}"
}

object LibFireBase {
    val auth = "com.google.firebase:firebase-auth:${Versions.fireBaseAuth}"
    val core = "com.google.firebase:firebase-core:${Versions.fireBaseCore}"
}

object LibCore {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val kotlinCoRoutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coRoutines}"
    val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    val multiDex = "androidx.multidex:multidex:${Versions.multiDex}"

    val room = "androidx.room:room-runtime:${Versions.room}"
    val roomRxJava = "androidx.room:room-rxjava2:${Versions.room}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"

    val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    val rxJavaExtensions = "com.github.akarnokd:rxjava2-extensions:${Versions.rxJavaExtensions}"

    val paperdb = "io.paperdb:paperdb:${Versions.paperdb}"
    val rxpaper = "com.github.pakoito:RxPaper2:${Versions.rxpaper}"

    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object LibNetwork {
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val rxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val coRoutineAdapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.coRoutineAdapter}"
    val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
}

object LibSupport {
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val design = "com.google.android.material:material:${Versions.design}"
    val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
}

object LibProtobuf {
    val main = "com.google.protobuf:protobuf-java:${Versions.protobuf}"
    val grpcOkHttp = "io.grpc:grpc-okhttp:${Versions.grpc}"
    val grpcMain = "io.grpc:grpc-protobuf:${Versions.grpc}"
    val grpcStub = "io.grpc:grpc-stub:${Versions.grpc}"
    val grpcAuth = "io.grpc:grpc-auth:${Versions.grpc}"
    val javaXAnnotations = "javax.annotation:javax.annotation-api:${Versions.javaXAnnotations}"
}

object LibTest {
    val junit = "junit:junit:${Versions.junit}"
    val kotlinTest = "io.kotlintest:kotlintest-runner-junit5:${Versions.kotlinTest}"
    val testRunner = "androidx.test:runner:${Versions.testRunner}"

    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    val assertjCore = "org.assertj:assertj-core:${Versions.assertjCore}"

    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockitoAndroid}"

    val koinTestExtension = "io.kotlintest:kotlintest-extensions-koin:${Versions.kotlinTest}"
    val koinTest = "org.koin:koin-test:${Versions.koin}"

    val lifecycleTesting = "androidx.arch.core:core-testing:${Versions.lifecycle}"
    val roomTesting = "android.arch.persistence.room:testing:1.1.1"
}
