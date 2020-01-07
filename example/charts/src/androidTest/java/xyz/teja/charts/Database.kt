package xyz.teja.charts

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import xyz.teja.charts.data.local.ChartsLocalDataSource
import xyz.teja.charts.data.remote.chartRemoteDataZone
import xyz.teja.charts.domain.model.ChartInfo
import xyz.teja.charts.domain.model.MarketPrice
import java.time.Instant
import java.time.LocalDate

/**
 * @author Teja-Konjeti
 * @since 14-Dec 2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */

@Database(entities = [ChartInfo::class, MarketPrice::class], version = 1)
@TypeConverters(LocalDateTypeConverter::class)
abstract class TestDatabase : RoomDatabase() {
    abstract fun chartLocalDataSource(): ChartsLocalDataSource
}

class LocalDateTypeConverter {
    @TypeConverter
    fun toDate(date: Long?): LocalDate? =
        if (date == null) {
            null
        } else {
            Instant.ofEpochSecond(date).atZone(chartRemoteDataZone).toLocalDate()
        }

    @TypeConverter
    fun toDateString(date: LocalDate?): Long? =
        date?.atStartOfDay(chartRemoteDataZone)?.toEpochSecond()
}

