package xyz.teja.charts.data.remote

import io.reactivex.functions.Function
import xyz.teja.charts.domain.model.ChartInfo
import xyz.teja.charts.domain.model.MarketPrice
import java.time.Instant
import java.time.ZoneId

/**
 * @author Teja-Konjeti
 * @since 14-Dec 2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */

internal val chartMapper =
    Function<Pair<ChartRaw, String>, Pair<ChartInfo, List<MarketPrice>>> { pair ->
        pair.first.run {
            if (status != "ok") throw Exception("Failure from server: Status is not \"ok\"")

            val info = ChartInfo(pair.second, name, unit, period, description)
            val prices = values.map {
                val date =
                    Instant.ofEpochSecond(it.time).atZone(ZoneId.systemDefault()).toLocalDate()
                MarketPrice("BC" /* Bit Coin*/, date, it.price)
            }

            Pair(info, prices)
        }
    }
