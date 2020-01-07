package xyz.teja.starter

import androidx.room.*
import org.koin.dsl.module
import xyz.teja.base.AppConfig
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

/**
 * @author Teja-Konjeti
 * @since 14-Dec 2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */

@Database(entities = [], version = 1)
@TypeConverters(LocalDateTypeConverter::class)
internal abstract class AppDatabase : RoomDatabase() {
//    abstract fun someLocalDataSource(): SomeLocalDataSource
}

class LocalDateTypeConverter {
    private val remoteDataZone: ZoneId = ZoneId.of("GMT")

    @TypeConverter
    fun toDate(date: Long?): LocalDate? =
        if (date == null) {
            null
        } else {
            Instant.ofEpochSecond(date).atZone(remoteDataZone).toLocalDate()
        }

    @TypeConverter
    fun toDateString(date: LocalDate?): Long? =
        date?.atStartOfDay(remoteDataZone)?.toEpochSecond()
}

val koinDbModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            AppConfig.DATABASE_NAME
        ).build()
    }

//    single { get<AppDatabase>().someLocalDataSource() }
}

