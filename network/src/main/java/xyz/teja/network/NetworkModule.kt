package xyz.teja.network

import org.koin.dsl.module
import xyz.teja.base.AppConfig
import xyz.teja.base.koinCacheDirDependency

/**
 * @author Teja-Konjeti
 * @since 14-Dec 2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */

val koinNetworkModule = module {
    single { NetworkRepositoryImpl() as NetworkRepository }
    single {
        get<NetworkRepository>().getNetworkClient(
            AppConfig.BASE_URL,
            get(koinCacheDirDependency),
            AppConfig.DEBUG
        )
    }
}
