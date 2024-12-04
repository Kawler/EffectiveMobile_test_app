package com.kawler.effmobile.ui.main_tickets

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kawler.effmobile.R
import com.kawler.effmobile.databinding.FragmentMainTicketsBinding

class MainTickets : Fragment() {

    private var _binding: FragmentMainTicketsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainTicketsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainTicketsBinding.inflate(inflater, container, false)

        binding.mainTicketsSearch.setOnClickListener {
            findNavController().navigate(R.id.navigation_search)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}