package xyz.teja.charts.data.remote

import com.google.gson.annotations.SerializedName

/**
 * @author Teja-Konjeti
 * @since 14-Dec 2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */

internal class ChartRaw constructor(
    @SerializedName("status") val status: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("unit") val unit: String = "",
    @SerializedName("period") val period: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("values") val values: List<MarketPriceRaw> = listOf()
)

internal class MarketPriceRaw constructor(
    @SerializedName("x") val time: Long = 0,
    @SerializedName("y") val price: Float = 0F
)
