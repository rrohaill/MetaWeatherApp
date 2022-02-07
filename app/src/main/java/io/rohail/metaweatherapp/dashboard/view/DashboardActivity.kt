package io.rohail.metaweatherapp.dashboard.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import io.rohail.metaweatherapp.databinding.ActivityDashboardBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    private val dashboardViewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeData()
    }

    private fun observeData() {
        lifecycleScope.launch {
            dashboardViewModel.locationCityNamesFlow.collectLatest {
                initView(it)
            }
        }
    }

    private fun initView(list: List<String>) {
        binding.viewPager.apply {
            adapter = DashboardViewPagerAdapter(this@DashboardActivity, list)
            //recreate page and show most recent data
            offscreenPageLimit = 1
        }
    }
}