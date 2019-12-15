package xyz.teja.network

import retrofit2.Retrofit
import java.io.File

/**
 * @author Teja-Konjeti
 * @since 14-Dec 2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */
interface NetworkRepository {
    // Generify later
    fun getNetworkClient(baseUrl: String, cacheDir: File, debug: Boolean = false): Retrofit
}