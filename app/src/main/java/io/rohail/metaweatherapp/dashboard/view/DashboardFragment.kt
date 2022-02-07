package io.rohail.metaweatherapp.dashboard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dagger.hilt.android.AndroidEntryPoint
import io.rohail.metaweatherapp.R
import io.rohail.metaweatherapp.dashboard.model.WeatherInfoUI
import io.rohail.metaweatherapp.dashboard.model.WeatherInfoUIResult
import io.rohail.metaweatherapp.databinding.DashboardFragmentBinding
import io.rohail.metaweatherapp.utilities.Extensions.visibleIf
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    companion object {
        private const val LOCATION = "LOCATION"

        fun newInstance(locationName: String) = DashboardFragment().apply {
            arguments = Bundle().apply {
                putString(LOCATION, locationName)
            }
        }
    }

    private val fragmentViewModel: DashboardFragmentViewModel by viewModels()

    private var _binding: DashboardFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DashboardFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentViewModel.setLocationName(getLocationName())
        initView()
        observeData()
        fragmentViewModel.fetchWeatherData()
    }

    private fun observeData() {
        lifecycleScope.launch {
            fragmentViewModel.getWeatherResultFlow().collectLatest { resultUI ->
                when (resultUI) {
                    is WeatherInfoUIResult.Success -> setUI(resultUI.data)
                    is WeatherInfoUIResult.Error -> binding.weatherIcon.setBackgroundResource(R.drawable.ic_error)
                }
            }
        }

    }

    private fun setLoadingVisibility(isVisible: Boolean) {
        binding.apply {
            swipeRefreshLayout.isRefreshing = isVisible
            mainLayout.visibleIf(!isVisible)
        }
    }

    private fun setUI(resultUI: WeatherInfoUI) {
        setLoadingVisibility(false)
        binding.weatherInfo = resultUI
        Glide.with(requireContext()).load(resultUI.iconUrl).fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_error)
            .into(binding.weatherIcon)
    }

    private fun initView() {
        setLoadingVisibility(true)
        binding.apply {
            viewModel = fragmentViewModel
            swipeRefreshLayout.setOnRefreshListener {
                swipeRefreshLayout.isRefreshing = true
                fragmentViewModel.fetchWeatherData()
            }
        }
    }

    private fun getLocationName(): String =
        arguments?.getString(LOCATION)
            ?: throw IllegalArgumentException("$LOCATION info not available")

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}