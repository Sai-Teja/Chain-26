package xyz.teja.charts.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.charts.R
import hu.akarnokd.rxjava2.math.MathObservable
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_chart.*
import kotlinx.android.synthetic.main.fragment_chart.view.*
import org.koin.android.ext.android.inject
import xyz.teja.charts.presentation.LoadingState.*
import java.time.Period
import java.time.format.DateTimeFormatter

/**
 * @author Teja-Konjeti
 * @since 16-Dec-2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */
class ChartsFragment : Fragment() {

    private val viewModel: ChartsMvvm by inject()

    private val adapter = GraphAdapter()

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_chart, null, true)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.graph.adapter = adapter

        viewModel.values.observe(this, Observer {
            adapter.data = it

            // Kotlin Map to avoid multiple calculations on subscription
            val prices = it.map { marketPrice -> marketPrice.price }
            val pricesObservable = Observable.fromIterable(prices)

            MathObservable.max(pricesObservable).subscribe { price ->
                maxPrice.text = resources.getString(R.string.max_min_price, price)
            }
            MathObservable.min(pricesObservable).subscribe { price ->
                minPrice.text = resources.getString(R.string.max_min_price, price)
            }

            val format = DateTimeFormatter.ISO_DATE

            startDate.text = it.firstOrNull()?.date?.format(format) ?: ""
            endDate.text = it.lastOrNull()?.date?.format(format) ?: ""
        })

        viewModel.loading.observe(this, Observer {
            when (it) {
                LOADING -> loadingBar.visibility = View.VISIBLE
                LOADED -> loadingBar.visibility = View.GONE
                ERROR, null ->
                    Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT)
                        .show()
            }
        })

        radioGroup.setOnCheckedChangeListener { _, checked -> load(checked) }

        load(radioGroup.checkedRadioButtonId)
    }

    private fun load(checked: Int) {
        when (checked) {
            R.id.week -> viewModel.period = Period.ofWeeks(1)
            R.id.month -> viewModel.period = Period.ofMonths(1)
            R.id.year -> viewModel.period = Period.ofYears(1)
        }
    }
}