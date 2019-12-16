package xyz.teja.charts.data

import android.annotation.SuppressLint
import io.reactivex.Completable
import io.reactivex.Observable
import xyz.teja.charts.KoinSchedulers
import xyz.teja.charts.data.local.ChartLocalDataSource
import xyz.teja.charts.data.remote.ChartRemoteDataSource
import xyz.teja.charts.data.remote.chartMapper
import xyz.teja.charts.domain.model.ChartInfo
import xyz.teja.charts.domain.model.MarketPrice
import xyz.teja.charts.domain.repository.ChartsRepository
import xyz.teja.charts.totalDays
import java.time.LocalDate
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

    @SuppressLint("CheckResult")
    // Don't need to be disposed as it is being updated in the background and is a Single
    override fun getPrices(period: Period): Observable<List<MarketPrice>> {
        val chartStartDate = LocalDate.now().minusDays(period.totalDays().toLong())

        local.getPricesOnce(chartStartDate)
            .subscribeOn(koinSchedulers.io())
            .subscribe { prices ->
                val enoughData =
                    prices.size >= period.totalDays() - 1 // API gives one less day sometimes
                val latestData =
                    prices.isNotEmpty() && prices.last().date == LocalDate.now().minusDays(1)

                if (!enoughData || !latestData) {
                    refresh(period).subscribe()
                }
            }

        return local.getPrices(chartStartDate)
            .subscribeOn(koinSchedulers.io())
    }

    override fun getChartInfo(period: Period): Observable<ChartInfo> {
        val timeSpan = "${period.totalDays()}days"

        return local.getChartInfo(timeSpan)
    }
}
