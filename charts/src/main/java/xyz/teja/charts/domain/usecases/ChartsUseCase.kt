package xyz.teja.charts.domain.usecases

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Observable.just
import xyz.teja.charts.data.remote.chartRemoteDateNow
import xyz.teja.charts.domain.model.MarketPrice
import xyz.teja.charts.domain.repository.ChartsRepository
import xyz.teja.charts.totalDays
import java.time.Period

/**
 * @author Teja-Konjeti
 * @since 14-Dec 2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */

// This might contain better logic if
// 1. We had a complex app with multiple repositories to handle
// 2. We had to map the Repository Object to an UI Object
internal class ChartsUseCase constructor(private val chartsRepository: ChartsRepository) {

    fun getPrices(period: Period): Observable<List<MarketPrice>> =
        // Rx When-And-Then
        chartsRepository.getPrices(period)
            .flatMap { fetchWhenRequired(it, period).andThen(just(it)) }

    private fun fetchWhenRequired(prices: List<MarketPrice>, period: Period): Completable {
        val enoughData =
            prices.size >= period.totalDays() - 1 // API gives one less day
        val latestData =
            prices.isNotEmpty() && prices.last().date == chartRemoteDateNow.minusDays(1)

        return if (!enoughData || !latestData) {
            chartsRepository.refresh(period)
        } else {
            Completable.complete()
        }
    }
}