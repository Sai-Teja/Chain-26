package xyz.teja.charts.data

import androidx.lifecycle.LiveData
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import xyz.teja.charts.data.local.ChartLocalDataSource
import xyz.teja.charts.data.remote.ChartRemoteDataSource
import xyz.teja.charts.data.remote.chartMapper
import xyz.teja.charts.domain.model.ChartInfo
import xyz.teja.charts.domain.model.MarketPrice
import xyz.teja.charts.domain.repository.ChartsRepository
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
    private val remote: ChartRemoteDataSource
) : ChartsRepository {

    override fun refresh(period: Period): Completable {
        val timeSpan = "${period.days}days"

        return remote.getChart(timeSpan = timeSpan)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.computation())
            .toObservable()
            .map { Pair(it, timeSpan) }
            .map(chartMapper)
            .map {
                local.addChartInfo(it.first)
                local.addPrices(it.second)
            }
            .ignoreElements()
    }

    override fun getPrices(period: Period): LiveData<List<MarketPrice>> {
        val prices = local.getPrices(LocalDate.now().minusDays(period.days.toLong()))

        if (prices.isEmpty() || prices.last().dateTime != LocalDate.now())
            refresh(period)

        return local.getPricesLive(LocalDate.now().minusDays(period.days.toLong()))
    }

    override fun getChartInfo(period: Period): LiveData<ChartInfo> {
        val timeSpan = "${period.days}days"

        return local.getChartInfo(timeSpan)
    }
}
