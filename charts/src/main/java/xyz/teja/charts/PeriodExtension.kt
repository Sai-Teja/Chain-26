package xyz.teja.charts

import java.time.Period

/**
 * @author Teja-Konjeti
 * @since 16-Dec-2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */

fun Period.totalDays(): Int = days + months * 30 + years * 365