package xyz.teja.charts.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
interface ChartLocalDataSource {
    @Query("SELECT * FROM chart_info WHERE identifier = :identifier")
    fun getChartInfo(identifier: String): LiveData<ChartInfo>

    @Query("SELECT * FROM market_price WHERE dateTime >= :startDate AND currencyName == :currencyName ORDER BY dateTime")
    fun getPricesLive(
        startDate: LocalDate,
        currencyName: String = "BC"
    ): LiveData<List<MarketPrice>>

    @Query("SELECT * FROM market_price WHERE dateTime >= :startDate AND currencyName == :currencyName ORDER BY dateTime")
    fun getPrices(startDate: LocalDate, currencyName: String = "BC"): List<MarketPrice>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addChartInfo(chartInfo: ChartInfo): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPrices(prices: List<MarketPrice>): Void
}