package xyz.teja.charts.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

/**
 * @author Teja-Konjeti
 * @since 16-Dec-2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */
@Entity(tableName = "chart_info")
data class ChartInfo(
    @PrimaryKey var identifier: String,
    val name: String,
    val unit: String,
    val period: String,
    var description: String
)

@Entity(tableName = "market_price")
data class MarketPrice(
    val currencyName: String,
    @PrimaryKey val dateTime: LocalDate,
    val price: Float
)
