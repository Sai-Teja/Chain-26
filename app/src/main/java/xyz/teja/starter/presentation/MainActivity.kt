package xyz.teja.starter.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import xyz.teja.starter.R
import xyz.teja.starter.injectKoinModules

/**
 * @author Teja-Konjeti
 * @since 14-Dec 2019
 * <p>
 * © Copyright 2019 Teja Konjeti. All Rights Reserved.
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectKoinModules()

        setContentView(R.layout.activity_main)

        setSupportActionBar(main_toolbar)
    }
}