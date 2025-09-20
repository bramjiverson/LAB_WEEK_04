package com.example.lab_week_04

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

// List tab names / resource IDs
val TABS_FIXED = listOf(
    R.string.starbucks_title,
    R.string.janjijiwa_title,
    R.string.kopikenangan_title
)

/**
 * Data class to hold cafe configuration (title resource ID and cafe ID).
 */
data class CafeConfig(val titleResId: Int, val cafeId: String)

/**
 * Adapter for ViewPager2 to display cafe menu fragments for Starbucks, Janji Jiwa, and Kopi Kenangan.
 * Each tab corresponds to a CafeDetailFragment with a specific title resource ID and cafe ID.
 */
class CafeAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    // Cache for fragment instances to improve performance
    private val fragmentCache = mutableMapOf<Int, Fragment>()

    // Cafe configurations mapping position to title resource ID and cafe ID
    private val cafeConfigs = listOf(
        CafeConfig(R.string.starbucks_title, "starbucks"),
        CafeConfig(R.string.janjijiwa_title, "janji_jiwa"),
        CafeConfig(R.string.kopikenangan_title, "kopi_kenangan")
    )

    /**
     * Returns the total number of tabs.
     */
    override fun getItemCount(): Int = cafeConfigs.size

    /**
     * Creates or retrieves a fragment for the given position, passing the corresponding title resource ID
     * and cafe ID to CafeDetailFragment.
     * @param position The position of the tab (0 for Starbucks, 1 for Janji Jiwa, 2 for Kopi Kenangan).
     * @return A CafeDetailFragment instance with the appropriate title resource ID and cafe ID.
     */
    override fun createFragment(position: Int): Fragment {
        // Return cached fragment if it exists
        fragmentCache[position]?.let { return it }

        // Get the cafe configuration for the given position, default to Starbucks if out of bounds
        val config = cafeConfigs.getOrElse(position) { cafeConfigs[0] }

        // Create new fragment and store it in cache
        val fragment = CafeDetailFragment.newInstance(config.titleResId, config.cafeId)
        fragmentCache[position] = fragment
        return fragment
    }

    /**
     * Clears the fragment cache when needed (e.g., when data needs to be refreshed).
     */
    fun clearFragmentCache() {
        fragmentCache.clear()
        notifyDataSetChanged()
    }
}