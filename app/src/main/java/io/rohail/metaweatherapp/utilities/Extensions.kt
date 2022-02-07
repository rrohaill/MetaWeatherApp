package io.rohail.metaweatherapp.utilities

import android.view.View

object Extensions {
    fun View.visibleIf(condition: Boolean?) {
        val cond = condition ?: false
        if (cond && visibility == View.VISIBLE || !cond && visibility == View.GONE) return
        visibility = if (cond) View.VISIBLE else View.GONE
    }
}