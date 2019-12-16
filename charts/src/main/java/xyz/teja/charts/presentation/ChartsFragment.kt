package xyz.teja.charts.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.charts.R
import kotlinx.android.synthetic.main.fragment_chart.*
import kotlinx.android.synthetic.main.fragment_chart.view.*
import org.koin.android.ext.android.inject
import java.time.Period

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