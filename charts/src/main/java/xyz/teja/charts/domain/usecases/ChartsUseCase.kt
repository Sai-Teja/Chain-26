package xyz.teja.charts.domain.usecases

import io.reactivex.Observable
import xyz.teja.charts.domain.model.MarketPrice
import xyz.teja.charts.domain.repository.ChartsRepository
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
        chartsRepository.getPrices(period)
}