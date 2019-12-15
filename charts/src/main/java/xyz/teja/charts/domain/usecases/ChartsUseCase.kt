package xyz.teja.charts.domain.usecases

import androidx.lifecycle.LiveData
import xyz.teja.charts.domain.model.ChartInfo
import xyz.teja.charts.domain.repository.ChartsRepository
import java.time.Period

/**
 * @author Teja-Konjeti
 * @since 14-Dec 2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */

// This might contain better logic if we had a complex app with multiple repositories
// to handle
class ChartsUseCase constructor(private val chartsRepository: ChartsRepository) {

    fun getChartInfo(period: Period): LiveData<ChartInfo> =
        chartsRepository.getChartInfo(period)
}