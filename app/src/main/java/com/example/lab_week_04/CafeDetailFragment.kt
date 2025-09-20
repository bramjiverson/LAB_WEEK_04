package com.example.lab_week_04

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CafeDetailFragment : Fragment() {

    companion object {
        private const val ARG_TITLE_RES_ID = "title_res_id"
        private const val ARG_CAFE_ID = "cafe_id"

        fun newInstance(titleResId: Int, cafeId: String): CafeDetailFragment {
            val fragment = CafeDetailFragment()
            val args = Bundle().apply {
                putInt(ARG_TITLE_RES_ID, titleResId)
                putString(ARG_CAFE_ID, cafeId)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val cafeId = arguments?.getString(ARG_CAFE_ID) ?: "starbucks"
        val layoutResId = when (cafeId) {
            "starbucks" -> R.layout.fragment_menu
            "janji_jiwa" -> R.layout.fragment_janjijiwa
            "kopi_kenangan" -> R.layout.fragment_kopikenangan
            else -> R.layout.fragment_menu
        }
        return inflater.inflate(layoutResId, container, false)
    }
}