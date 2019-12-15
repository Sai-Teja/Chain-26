package xyz.teja.charts.domain.repository

import androidx.lifecycle.LiveData
import io.reactivex.Completable
import xyz.teja.charts.domain.model.ChartInfo
import xyz.teja.charts.domain.model.MarketPrice
import java.time.Period

/**
 * @author Teja-Konjeti
 * @since 14-Dec 2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */

interface ChartsRepository {
    fun refresh(period: Period): Completable
    fun getPrices(period: Period): LiveData<List<MarketPrice>>
    fun getChartInfo(period: Period): LiveData<ChartInfo>
}