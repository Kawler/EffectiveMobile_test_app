package com.kawler.effmobile.ui.fragments.main_tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.kawler.effmobile.databinding.FragmentMainTicketsBinding
import com.kawler.effmobile.domain.utils.SharedPreferencesUtil
import com.kawler.effmobile.ui.adapters.OfferRVAdapter
import com.kawler.effmobile.ui.fragments.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainTickets : Fragment() {

    private var _binding: FragmentMainTicketsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainTicketsViewModel by viewModels()

    @Inject
    lateinit var sharedPreferencesUtil: SharedPreferencesUtil

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainTicketsBinding.inflate(inflater, container, false)
        setupUI()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        observeViewModel()
    }

    private fun setupUI() {
        binding.mainTicketsTvCityDeparture.text = sharedPreferencesUtil.getUserCity()

        val adapter = OfferRVAdapter()
        binding.mainTicketsRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.mainTicketsRv.adapter = adapter
    }

    private fun setupListeners() {
        binding.mainTicketsSearch.setOnClickListener {
            SearchFragment().show(childFragmentManager, "Search")
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.offers.collect { response ->
                    (binding.mainTicketsRv.adapter as? OfferRVAdapter)?.updateData(response)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
