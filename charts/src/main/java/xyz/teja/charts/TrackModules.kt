package xyz.teja.charts

import org.koin.dsl.module
import retrofit2.Retrofit
import xyz.teja.charts.data.ChartsRepositoryImpl
import xyz.teja.charts.data.remote.ChartRemoteDataSource
import xyz.teja.charts.domain.repository.ChartsRepository
import xyz.teja.charts.domain.usecases.ChartsUseCase

/**
 * @author Teja-Konjeti
 * @since 14-Dec 2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */

val koinChartsModule = module {
    single { get<Retrofit>().create(ChartRemoteDataSource::class.java) }
    single {
        ChartsRepositoryImpl(
            get(),
            get()
        ) as ChartsRepository
    }
    single { ChartsUseCase(get()) }
}
