package com.kawler.effmobile.ui.fragments.search

import android.os.Bundle
import android.text.InputFilter
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kawler.effmobile.R
import com.kawler.effmobile.databinding.FragmentSearchBinding
import com.kawler.effmobile.domain.utils.SharedPreferencesUtil
import com.kawler.effmobile.ui.adapters.CitySuggestionsAdapter
import com.kawler.effmobile.ui.fragments.main_tickets.MainTicketsDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()
    @Inject
    lateinit var sharedPreferencesUtil: SharedPreferencesUtil

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        setupRecyclerView()
        setupListeners()
        setupInputFilters()
        setupInitialDeparture()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adjustDialogHeight()
    }

    private fun setupInitialDeparture() {
        binding.searchTvCityDeparture.setText(sharedPreferencesUtil.getUserCity())
    }

    private fun setupRecyclerView() {
        val adapter = CitySuggestionsAdapter()
        binding.searchRvCities.adapter = adapter
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.cities.collect { response ->
                    (binding.searchRvCities.adapter as? CitySuggestionsAdapter)?.updateData(response)
                }
            }
        }
        binding.searchRvCities.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupListeners() {
        binding.searchBtnHot.setOnClickListener {
            navigateToHotTickets()
        }

        binding.searchBtnHard.setOnClickListener {
            navigateToHardRoute()
        }

        binding.searchBtnWeekend.setOnClickListener {
            navigateToWeekends()
        }

        binding.searchBtnAnywhere.setOnClickListener {
            setAnywhereCityDestination()
        }

        binding.searchClearText.setEndIconOnClickListener {
            clearCityDestination()
        }

        binding.searchTvCityDeparture.setOnEditorActionListener { _, actionId, event ->
            handleCityDepartureAction(actionId, event)
        }

        binding.searchTvCityDestination.setOnEditorActionListener { _, actionId, event ->
            handleCityDestinationAction(actionId, event)
        }
    }

    private fun navigateToHotTickets() {
        findNavController().navigate(MainTicketsDirections.actionNavigationMainTicketsToHotTicketsFragment())
    }

    private fun navigateToHardRoute() {
        findNavController().navigate(MainTicketsDirections.actionNavigationMainTicketsToHardRouteFragment())
    }

    private fun navigateToWeekends() {
        findNavController().navigate(MainTicketsDirections.actionNavigationMainTicketsToWeekendsFragment())
    }

    private fun setAnywhereCityDestination() {
        binding.searchTvCityDestination.setText("Куда угодно")
        if (!binding.searchTvCityDeparture.text.isNullOrEmpty())
            findNavController().navigate(MainTicketsDirections.actionNavigationMainTicketsToSearchChosenFragment(
                binding.searchTvCityDestination.text.toString().trim()
            ))
    }

    private fun clearCityDestination() {
        binding.searchTvCityDestination.text?.clear()
    }

    private fun handleCityDepartureAction(actionId: Int, event: KeyEvent?): Boolean {
        if (isDoneAction(actionId, event)) {
            val query = binding.searchTvCityDestination.text.toString().trim()
            if (query.isNotEmpty()) {
                sharedPreferencesUtil.setUserCity(binding.searchTvCityDeparture.text.toString())
            }
            return true
        }
        return false
    }

    private fun handleCityDestinationAction(actionId: Int, event: KeyEvent?): Boolean {
        if (isDoneAction(actionId, event)) {
            val query = binding.searchTvCityDestination.text.toString().trim()
            if (query.isNotEmpty()) {
                navigateToSearchChosenFragment(query)
            }
            return true
        }
        return false
    }

    private fun isDoneAction(actionId: Int, event: KeyEvent?): Boolean {
        return actionId == EditorInfo.IME_ACTION_DONE ||
                (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)
    }

    private fun navigateToSearchChosenFragment(query: String) {
        val navController = findNavController()
        val currentDestinationId = navController.currentDestination?.id
        if (currentDestinationId != R.id.searchChosenFragment) {
            navController.navigate(
                MainTicketsDirections.actionNavigationMainTicketsToSearchChosenFragment(query)
            )
        }
    }

    private fun setupInputFilters() {
        val inputFilter = InputFilter { source, start, end, _, _, _ ->
            val regex = "[а-яА-ЯёЁ ]"
            source.substring(start, end).filter { it.toString().matches(regex.toRegex()) }
        }
        binding.searchTvCityDeparture.filters = arrayOf(inputFilter)
        binding.searchTvCityDestination.filters = arrayOf(inputFilter)
        binding.searchTvCityDestination.imeOptions = EditorInfo.IME_ACTION_DONE
    }

    private fun adjustDialogHeight() {
        val screenHeight = resources.displayMetrics.heightPixels
        val dialogHeight = (screenHeight * 0.9).toInt()
        binding.root.layoutParams =
            FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, dialogHeight)
        val behavior =
            BottomSheetBehavior.from(dialog?.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialogTheme
    }
}
