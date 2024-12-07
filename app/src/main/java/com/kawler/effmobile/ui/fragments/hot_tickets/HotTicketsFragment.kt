package com.kawler.effmobile.ui.fragments.hot_tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kawler.effmobile.R

class HotTicketsFragment : Fragment() {

    companion object {
        fun newInstance() = HotTicketsFragment()
    }

    private val viewModel: HotTicketsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_hot_tickets, container, false)
    }
}