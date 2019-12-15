package xyz.teja.chain26

import android.content.Context
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import xyz.teja.chain26.presentation.ChartsMvvm
import xyz.teja.charts.koinChartsModule
import xyz.teja.network.koinCacheDirDependency
import xyz.teja.network.koinNetworkModule

/**
 * @author Teja-Konjeti
 * @since 14-Dec 2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */

val koinModules by lazy {
    listOf(
        koinBasicsModule, koinDbModule, koinNetworkModule,
        koinChartsModule, koinViewModels // Order matters
    )
}

private val koinBasicsModule = module {
    single(koinCacheDirDependency) { get<Context>().cacheDir }
}

private val koinViewModels = module {
    viewModel { ChartsMvvm(get()) }
}
