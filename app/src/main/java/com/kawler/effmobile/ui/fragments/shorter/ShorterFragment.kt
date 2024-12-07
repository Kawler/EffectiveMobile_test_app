package com.kawler.effmobile.ui.fragments.shorter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kawler.effmobile.R

class ShorterFragment : Fragment() {

    companion object {
        fun newInstance() = ShorterFragment()
    }

    private val viewModel: ShorterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_shorter, container, false)
    }
}