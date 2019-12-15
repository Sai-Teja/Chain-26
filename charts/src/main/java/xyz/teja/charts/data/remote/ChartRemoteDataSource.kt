package xyz.teja.charts.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Teja-Konjeti
 * @since 14-Dec 2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */

internal interface ChartRemoteDataSource {

    @GET("/charts/market-price")
    fun getChart(
        @Query("timespan") timeSpan: String,
        @Query("format") format: String = "json"
    ): Single<ChartRaw>
}