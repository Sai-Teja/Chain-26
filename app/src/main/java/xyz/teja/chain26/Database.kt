package xyz.teja.chain26

import androidx.room.*
import org.koin.dsl.module
import xyz.teja.charts.data.local.ChartLocalDataSource
import xyz.teja.charts.domain.model.ChartInfo
import xyz.teja.charts.domain.model.MarketPrice
import java.time.LocalDate

/**
 * @author Teja-Konjeti
 * @since 14-Dec 2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */

@Database(entities = [ChartInfo::class, MarketPrice::class], version = 1)
@TypeConverters(LocalDateTypeConverter::class)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun chartLocalDataSource(): ChartLocalDataSource
}

class LocalDateTypeConverter {
    @TypeConverter
    fun toDate(dateString: String?): LocalDate? =
        if (dateString == null) null else LocalDate.parse(dateString)

    @TypeConverter
    fun toDateString(date: LocalDate?): String? = date?.toString()
}

val koinDbModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java, "chart_database"
        ).build()
    }
    single { get<AppDatabase>().chartLocalDataSource() }
}

