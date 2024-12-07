package com.kawler.effmobile.ui.fragments.subscricptions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kawler.effmobile.R

class SubscriptionsFragment : Fragment() {

    companion object {
        fun newInstance() = SubscriptionsFragment()
    }

    private val viewModel: SubscriptionsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_subscriptions, container, false)
    }
}