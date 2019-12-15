package xyz.teja.network

import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * @author Teja-Konjeti
 * @since 14-Dec 2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */

private const val BASE_URL = "https://api.blockchain.info/"

val koinCacheDirDependency = named("cacheDir")

val koinNetworkModule = module {
    single { NetworkRepositoryImpl() as NetworkRepository }
    single {
        get<NetworkRepository>().getNetworkClient(
            BASE_URL,
            get(koinCacheDirDependency),
            true
        )
    }
}
