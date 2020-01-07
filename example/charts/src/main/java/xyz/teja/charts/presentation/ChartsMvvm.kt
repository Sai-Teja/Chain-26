package xyz.teja.charts.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.akarnokd.rxjava2.math.MathObservable
import io.reactivex.Observable
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
    private var pricesDisposable = CompositeDisposable()

    private val mValues = MutableLiveData<List<MarketPrice>>()
    val values = mValues as LiveData<List<MarketPrice>>

    private val mMinPrice = MutableLiveData<Float>()
    val minPrice = mMinPrice as LiveData<Float>

    private val mMaxPrice = MutableLiveData<Float>()
    val maxPrice = mMaxPrice as LiveData<Float>

    private val mLoading = MutableLiveData<LoadingState>(LoadingState.LOADED)
    val loading = mLoading as LiveData<LoadingState>

    var period: Period = Period.ZERO
        set(newPeriod) {
            this.pricesDisposable.clear()
            this.mLoading.postValue(LoadingState.LOADING)
            this.pricesDisposable.add(
                this.useCase
                    .getPrices(newPeriod)
                    .subscribe(
                        {
                            mValues.postValue(it)

                            // Kotlin Map to avoid multiple calculations on subscription
                            val prices = it.map { marketPrice -> marketPrice.price }
                            val pricesObservable = Observable.fromIterable(prices)

                            MathObservable.min(pricesObservable).subscribe { price ->
                                mMinPrice.postValue(price)
                            }

                            MathObservable.max(pricesObservable).subscribe { price ->
                                mMaxPrice.postValue(price)
                            }

                            mLoading.postValue(LoadingState.LOADED)
                        },
                        // Throwable can be used to determine the error for a granular error message
                        { this.mLoading.postValue(LoadingState.ERROR) }
                    )
            )
            field = newPeriod
        }
}

enum class LoadingState {
    LOADING,
    LOADED,
    ERROR
}
