package com.kawler.effmobile.ui.fragments.weekends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kawler.effmobile.R

class WeekendsFragment : Fragment() {

    companion object {
        fun newInstance() = WeekendsFragment()
    }

    private val viewModel: WeekendsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_weekends, container, false)
    }
}