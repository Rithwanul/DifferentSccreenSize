package com.example.differentscreensize

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.window.layout.WindowMetricsCalculator
import com.example.differentscreensize.databinding.ActivityMainBinding
import com.example.differentscreensize.enums.WindowSizeClass

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val container : ViewGroup = mBinding.container
        container.addView(object : View(this) {
            override fun onConfigurationChanged(newConfig: Configuration?) {
                super.onConfigurationChanged(newConfig)
                computeWindowSizeClass()
            }
        })

        computeWindowSizeClass()
    }

    private fun computeWindowSizeClass() {
        val metrics =
            WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(this)
        val widthDp = metrics.bounds.width() / resources.displayMetrics.density
        val windthWidowSizeClass = when {
            widthDp < 600f -> WindowSizeClass.COMPACT
            widthDp < 840f -> WindowSizeClass.MEDIUM
            else -> WindowSizeClass.EXPANDED
        }

        val heightDp = metrics.bounds.height() / resources.displayMetrics.density
        val heightWindowSizeClass = when {
            heightDp < 480f -> WindowSizeClass.COMPACT
            heightDp < 900f -> WindowSizeClass.MEDIUM
            else -> WindowSizeClass.EXPANDED
        }

        Log.d("Data", "Window Size in height : $heightWindowSizeClass")
        Log.d("Data", "Window Size in width : $windthWidowSizeClass")
    }
}