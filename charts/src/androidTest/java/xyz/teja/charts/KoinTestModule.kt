package xyz.teja.charts

import androidx.room.Room
import org.koin.dsl.module
import xyz.teja.charts.data.ChartsRepositoryImpl
import xyz.teja.charts.data.remote.ChartRemoteDataSource
import xyz.teja.charts.domain.repository.ChartsRepository

/**
 * @author Teja-Konjeti
 * @since 16-Dec-2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */

val koinTestModule = module {
    single { KoinTestSchedulers() as KoinSchedulers }
    single { ChartRemoteDataMock() as ChartRemoteDataSource }
    single {
        Room.inMemoryDatabaseBuilder(get(), TestDatabase::class.java)
            .build()
            .chartLocalDataSource()
    }
    single { ChartsRepositoryImpl(get(), get(), get()) as ChartsRepository }
}