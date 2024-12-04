package com.kawler.effmobile.ui.search_chosen

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kawler.effmobile.R

class SearchChosenFragment : Fragment() {

    companion object {
        fun newInstance() = SearchChosenFragment()
    }

    private val viewModel: SearchChosenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_search_chosen, container, false)
    }
}