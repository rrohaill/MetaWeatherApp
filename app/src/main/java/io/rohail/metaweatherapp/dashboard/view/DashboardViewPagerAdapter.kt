package io.rohail.metaweatherapp.dashboard.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class DashboardViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val locationList: List<String>
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return locationList.size
    }

    override fun createFragment(position: Int): Fragment {
        return DashboardFragment.newInstance(locationList[position])
    }
}