package xyz.teja.charts.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable
import io.reactivex.Single
import xyz.teja.charts.domain.model.ChartInfo
import xyz.teja.charts.domain.model.MarketPrice
import java.time.LocalDate

/**
 * @author Teja-Konjeti
 * @since 14-Dec 2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */

@Dao
interface ChartsLocalDataSource {
    @Query("SELECT * FROM chart_info WHERE identifier = :identifier")
    fun getChartInfo(identifier: String): Observable<ChartInfo>

    @Query("SELECT * FROM market_price WHERE date >= :startDate AND currencyName == :currencyName ORDER BY date")
    fun getPrices(
        startDate: LocalDate,
        currencyName: String = "BC"
    ): Observable<List<MarketPrice>>

    @Query("SELECT * FROM market_price WHERE date >= :startDate AND currencyName == :currencyName ORDER BY date")
    fun getPricesOnce(
        startDate: LocalDate,
        currencyName: String = "BC"
    ): Single<List<MarketPrice>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addChartInfo(chartInfo: ChartInfo): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPrices(prices: List<MarketPrice>): Void
}