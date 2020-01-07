package xyz.teja.charts

import androidx.room.Room
import org.koin.dsl.module
import xyz.teja.charts.data.ChartsRepositoryImpl
import xyz.teja.charts.data.remote.ChartsRemoteDataSource
import xyz.teja.charts.domain.repository.ChartsRepository
import xyz.teja.charts.domain.usecases.ChartsUseCase

/**
 * @author Teja-Konjeti
 * @since 16-Dec-2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */

val koinChartsTestModule = module {
    single { ChartsRemoteDataMock() as ChartsRemoteDataSource }
    single {
        Room.inMemoryDatabaseBuilder(get(), TestDatabase::class.java)
            .build()
            .chartLocalDataSource()
    }
    single { ChartsRepositoryImpl(get(), get(), get()) as ChartsRepository }
    single { ChartsUseCase(get()) }
}