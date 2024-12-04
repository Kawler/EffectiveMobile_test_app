package com.kawler.effmobile.ui.search

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kawler.effmobile.R
import com.kawler.effmobile.databinding.FragmentSearchBinding

class SearchFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
adjustDialogHeight()

        return binding.root
    }

    private fun adjustDialogHeight() {
        val screenHeight = resources.displayMetrics.heightPixels
        val dialogHeight = (screenHeight * 0.8).toInt()
        binding.root.layoutParams =
            FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, dialogHeight)
    }
}