package xyz.teja.charts.presentation

import com.robinhood.spark.SparkAdapter
import xyz.teja.charts.domain.model.MarketPrice

/**
 * @author Teja-Konjeti
 * @since 16-Dec-2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */
internal class GraphAdapter : SparkAdapter() {

    var data: List<MarketPrice>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getY(index: Int): Float {
        return data?.get(index)?.price ?: 0f
    }

    override fun getItem(index: Int): Any? {
        return data?.get(index)
    }

    override fun getCount() = data?.size ?: 0

}