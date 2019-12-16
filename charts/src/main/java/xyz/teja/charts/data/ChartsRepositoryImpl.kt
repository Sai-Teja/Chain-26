package xyz.teja.charts.data

import io.reactivex.Completable
import io.reactivex.Observable
import xyz.teja.charts.KoinSchedulers
import xyz.teja.charts.data.local.ChartLocalDataSource
import xyz.teja.charts.data.remote.ChartRemoteDataSource
import xyz.teja.charts.data.remote.chartMapper
import xyz.teja.charts.data.remote.chartRemoteDateNow
import xyz.teja.charts.domain.model.ChartInfo
import xyz.teja.charts.domain.model.MarketPrice
import xyz.teja.charts.domain.repository.ChartsRepository
import xyz.teja.charts.totalDays
import java.time.Period

/**
 * @author Teja-Konjeti
 * @since 14-Dec 2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */

internal class ChartsRepositoryImpl constructor(
    private val local: ChartLocalDataSource,
    private val remote: ChartRemoteDataSource,
    private val koinSchedulers: KoinSchedulers
) : ChartsRepository {

    override fun refresh(period: Period): Completable {
        val timeSpan = "${period.totalDays()}days"

        return remote.getChart(timeSpan = timeSpan)
            .subscribeOn(koinSchedulers.computation())
            .map { Pair(it, timeSpan) }
            .map(chartMapper)
            .observeOn(koinSchedulers.io())
            .doOnSuccess {
                local.addChartInfo(it.first)
                local.addPrices(it.second)
            }
            .ignoreElement()
    }

    override fun getPrices(period: Period): Observable<List<MarketPrice>> {
        val chartStartDate = chartRemoteDateNow.minusDays(period.totalDays().toLong())

        return local.getPrices(chartStartDate)
            .subscribeOn(koinSchedulers.io())
    }

    override fun getChartInfo(period: Period): Observable<ChartInfo> {
        val timeSpan = "${period.totalDays()}days"

        return local.getChartInfo(timeSpan)
    }
}
