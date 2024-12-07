package com.kawler.effmobile.ui.fragments.alltickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kawler.effmobile.databinding.FragmentAllTicketsBinding
import com.kawler.effmobile.ui.adapters.TicketsDetailsRVAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllTicketsFragment : Fragment() {

    private var _binding: FragmentAllTicketsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AllTicketsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        observeViewModel()
    }

    private fun setupUI() {
        val args: AllTicketsFragmentArgs by navArgs()

        binding.flightDetailsRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (lastVisibleItem == totalItemCount - 1) {
                    binding.floatingButtonsContainer.visibility = View.GONE
                } else {
                    binding.floatingButtonsContainer.visibility = View.VISIBLE
                }
            }
        })

        val data = args.TicketSetting
        binding.flightDetailsTvRoute.text = data.route
        binding.flightDetailsRouteDetails.text = "${data.date}, ${data.passengers} пассажир"
        binding.flightDetailsRv.layoutManager = LinearLayoutManager(requireContext())
        binding.flightDetailsRv.adapter = TicketsDetailsRVAdapter()

        binding.flightDetailsBtnReturn.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.tickets.collect { response ->
                    (binding.flightDetailsRv.adapter as? TicketsDetailsRVAdapter)?.updateTickets(
                        response
                    )
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
