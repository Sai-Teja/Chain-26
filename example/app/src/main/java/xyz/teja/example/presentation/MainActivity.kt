package xyz.teja.example.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import xyz.teja.example.R
import xyz.teja.charts.presentation.ChartsFragment
import xyz.teja.example.injectKoinModules

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

        injectKoinModules()

        setContentView(R.layout.activity_main)

        setSupportActionBar(main_toolbar)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.charts, chartsFragment)
            .commit()
    }
}