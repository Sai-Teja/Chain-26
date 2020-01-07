package xyz.teja.base

import android.content.Context
import org.koin.core.qualifier.named
import org.koin.dsl.module

val koinCacheDirDependency = named("cacheDir")

val koinBasicsModule = module {
    single(koinCacheDirDependency) { get<Context>().cacheDir }

    single { KoinMainSchedulers() as KoinSchedulers }
}

val koinBasicsTestModule = module {
    single(koinCacheDirDependency) { get<Context>().cacheDir }

    single { KoinTestSchedulers() as KoinSchedulers }
}
