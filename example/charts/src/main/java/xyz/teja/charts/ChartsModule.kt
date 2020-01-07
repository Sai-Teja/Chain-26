package xyz.teja.charts

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import xyz.teja.charts.data.ChartsRepositoryImpl
import xyz.teja.charts.data.remote.ChartsRemoteDataSource
import xyz.teja.charts.domain.repository.ChartsRepository
import xyz.teja.charts.domain.usecases.ChartsUseCase
import xyz.teja.charts.presentation.ChartsFragment
import xyz.teja.charts.presentation.ChartsMvvm

/**
 * @author Teja-Konjeti
 * @since 14-Dec 2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */

val koinChartsModule = module {
    single { get<Retrofit>().create(ChartsRemoteDataSource::class.java) }
    single {
        ChartsRepositoryImpl(
            get(),
            get(),
            get()
        ) as ChartsRepository
    }
    single { ChartsUseCase(get()) }
    viewModel { ChartsMvvm(get()) }
    single { ChartsFragment() }
}
