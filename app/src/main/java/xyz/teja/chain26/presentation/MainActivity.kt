package xyz.teja.chain26.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.inject
import xyz.teja.chain26.R
import xyz.teja.charts.presentation.ChartsFragment

/**
 * @author Teja-Konjeti
 * @since 14-Dec 2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */

class MainActivity : AppCompatActivity() {

    private val chartsFragment: ChartsFragment by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.main_toolbar))

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.charts, chartsFragment)
            .commit()
    }
}