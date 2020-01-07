package xyz.teja.charts

import xyz.teja.charts.data.remote.chartRemoteDataZone
import xyz.teja.charts.data.remote.chartRemoteDateNow
import xyz.teja.charts.domain.model.ChartInfo

/**
 * @author Teja-Konjeti
 * @since 16-Dec-2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */

object TestCase {
    internal val jsonResponse = """{
"status": "ok",
"name": "Market Price (USD)",
"unit": "USD",
"period": "day",
"description": "Average USD market price across major bitcoin exchanges.",
"values": [
{
"x": ${chartRemoteDateNow.atStartOfDay(chartRemoteDataZone).toInstant().epochSecond},
"y": 8695.077142857142
}]
}"""

    internal val chartInfo = ChartInfo(
        "1day",
        "Market Price (USD)",
        "USD",
        "day",
        "Average USD market price across major bitcoin exchanges."
    )
}