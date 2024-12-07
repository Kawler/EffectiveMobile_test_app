package com.kawler.effmobile.ui.fragments.hard_route

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kawler.effmobile.R

class HardRouteFragment : Fragment() {

    companion object {
        fun newInstance() = HardRouteFragment()
    }

    private val viewModel: HardRouteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_hard_route, container, false)
    }
}