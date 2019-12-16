package xyz.teja.charts.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import xyz.teja.charts.domain.model.MarketPrice
import xyz.teja.charts.domain.usecases.ChartsUseCase
import java.time.Period

/**
 * @author Teja-Konjeti
 * @since 16-Dec-2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */

internal class ChartsMvvm(private val useCase: ChartsUseCase) : ViewModel() {
    var period: Period = Period.ZERO
        set(value) {
            this.pricesDisposable.clear()
            this.pricesDisposable.add(
                this.useCase
                    .getPrices(value)
                    .subscribe { values.postValue(it) }
            )
            field = value
        }

    private var pricesDisposable = CompositeDisposable()

    val values = MutableLiveData<List<MarketPrice>>()
}