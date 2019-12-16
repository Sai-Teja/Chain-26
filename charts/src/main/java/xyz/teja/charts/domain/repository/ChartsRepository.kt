package xyz.teja.charts.domain.repository

import io.reactivex.Completable
import io.reactivex.Observable
import xyz.teja.charts.domain.model.ChartInfo
import xyz.teja.charts.domain.model.MarketPrice
import java.time.Period

/**
 * @author Teja-Konjeti
 * @since 14-Dec 2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */

internal interface ChartsRepository {
    fun refresh(period: Period): Completable
    fun getPrices(period: Period): Observable<List<MarketPrice>>
    fun getChartInfo(period: Period): Observable<ChartInfo>
}