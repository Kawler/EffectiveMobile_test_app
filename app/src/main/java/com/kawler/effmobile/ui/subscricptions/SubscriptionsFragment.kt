package com.kawler.effmobile.ui.subscricptions

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kawler.effmobile.R

class SubscriptionsFragment : Fragment() {

    companion object {
        fun newInstance() = SubscriptionsFragment()
    }

    private val viewModel: SubscriptionsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_subscriptions, container, false)
    }
}