package xyz.teja.charts

import com.google.gson.Gson
import io.reactivex.Single
import xyz.teja.charts.data.remote.ChartRaw
import xyz.teja.charts.data.remote.ChartsRemoteDataSource

/**
 * @author Teja-Konjeti
 * @since 16-Dec-2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */
internal class ChartsRemoteDataMock : ChartsRemoteDataSource {
    override fun getChart(timeSpan: String, format: String): Single<ChartRaw> {
        return Single.create {
            it.onSuccess(
                Gson().fromJson(
                    TestCase.jsonResponse,
                    ChartRaw::class.java
                )
            )
        }
    }
}