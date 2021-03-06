package xyz.teja.example

import org.koin.core.context.loadKoinModules
import xyz.teja.base.koinBasicsModule
import xyz.teja.charts.koinChartsModule
import xyz.teja.network.koinNetworkModule

/**
 * @author Teja-Konjeti
 * @since 14-Dec 2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */

fun injectKoinModules() = loadKoinModules(listOf(
    koinBasicsModule,
    koinDbModule,
    koinNetworkModule,
    koinChartsModule// Order matters
))
