package com.kawler.effmobile.ui.fragments.search_chosen

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.kawler.effmobile.R
import com.kawler.effmobile.databinding.FragmentSearchChosenBinding
import com.kawler.effmobile.domain.models.TicketSettings
import com.kawler.effmobile.domain.utils.SharedPreferencesUtil
import com.kawler.effmobile.ui.adapters.TicketsRVAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

@AndroidEntryPoint
class SearchChosenFragment : Fragment() {

    private var _binding: FragmentSearchChosenBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchChosenViewModel by viewModels()
    private var selectedDate: String = ""

    @Inject
    lateinit var sharedPreferencesUtil: SharedPreferencesUtil

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchChosenBinding.inflate(inflater, container, false)
        binding.searchChosenRv.layoutManager = LinearLayoutManager(requireContext())

        selectedDate = viewModel.selectedDate.value ?:
                SimpleDateFormat("yyyy-MM-dd", Locale("ru"))
                    .format(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))
        binding.searchChosenBtnDate.text =
            SimpleDateFormat("yyyy-MM-dd", Locale("ru")).parse(selectedDate)?.let {
                SimpleDateFormat("d MMM, E", Locale("ru"))
                    .format(it)
            }

        val args: SearchChosenFragmentArgs by navArgs()
        val data = args.destination
        binding.searchChosenTvCityDestination.setText(data)

        val adapter = TicketsRVAdapter()
        binding.searchChosenRv.adapter = adapter

        setupSearchFilters()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.ticketsOffers.collect { response ->
                    (binding.searchChosenRv.adapter as? TicketsRVAdapter)?.setTickets(response)
                }
            }
            viewModel.ticketsOffers.collect { response ->
                adapter.setTickets(response.take(3))
            }
        }

        return binding.root
    }

    private fun setupSearchFilters() {
        val inputFilter = InputFilter { source, start, end, _, _, _ ->
            val regex = "[а-яА-ЯёЁ ]"
            val filteredSource = StringBuilder()

            for (i in start until end) {
                val char = source[i]
                if (char.toString().matches(regex.toRegex())) {
                    filteredSource.append(char)
                }
            }

            filteredSource.toString().takeIf { it.isNotEmpty() } ?: ""
        }

        binding.searchChosenTvCityDeparture.filters = arrayOf(inputFilter)
        binding.searchChosenTvCityDestination.filters = arrayOf(inputFilter)
        binding.searchChosenTvCityDeparture.setText(sharedPreferencesUtil.getUserCity())
        binding.searchChosenBtnDate.text = styleText(binding.searchChosenBtnDate.text.toString())

        binding.searchChosenTvCityDeparture.setOnEditorActionListener { _, actionId, event ->
            handleEditorAction(actionId, event, binding.searchChosenTvCityDeparture.text)
        }
    }

    private fun handleEditorAction(actionId: Int, event: KeyEvent?, text: Editable?): Boolean {
        return if (actionId == EditorInfo.IME_ACTION_DONE || event?.isEnterKeyEvent() == true) {
            val query = text.toString().trim()
            if (query.isNotEmpty()) {
                sharedPreferencesUtil.setUserCity(query)
            }
            true
        } else {
            false
        }
    }

    private fun KeyEvent.isEnterKeyEvent(): Boolean {
        return this.keyCode == KeyEvent.KEYCODE_ENTER && this.action == KeyEvent.ACTION_DOWN
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            searchChosenBtnReturn.setOnClickListener { openMaterialDatePicker(false) }
            searchChosenBtnDate.setOnClickListener { openMaterialDatePicker(true) }
            searchChosenBtnShowAll.setOnClickListener { handleShowAllButtonClick() }
            searchChosenBtnBack.setOnClickListener { findNavController().navigateUp() }

            searchChosenClearText.setEndIconOnClickListener { searchChosenTvCityDestination.text?.clear() }
            searchChosenSwapText.setEndIconOnClickListener { swapCities() }
        }
    }

    private fun swapCities() {
        val temp = binding.searchChosenTvCityDeparture.text
        binding.searchChosenTvCityDeparture.text = binding.searchChosenTvCityDestination.text
        binding.searchChosenTvCityDestination.text = temp
    }

    private fun handleShowAllButtonClick() {
        sharedPreferencesUtil.setUserCity(binding.searchChosenTvCityDeparture.text.toString())

        if (!binding.searchChosenTvCityDeparture.text.isNullOrEmpty()) {
            findNavController().navigate(
                SearchChosenFragmentDirections.actionSearchChosenFragmentToAllTicketsFragment(
                    TicketSettings(
                        date = selectedDate,
                        passengers = "1",
                        route = getRoute()
                    )
                )
            )
        } else {
            Toast.makeText(
                requireContext(),
                "Должен быть указан город отправления",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun getRoute(): String {
        return if (binding.searchChosenTvCityDestination.text.isNullOrEmpty()) {
            binding.searchChosenTvCityDeparture.text.toString()
        } else {
            "${binding.searchChosenTvCityDeparture.text}-${binding.searchChosenTvCityDestination.text}"
        }
    }

    private fun styleText(text: String): SpannableString {
        val spannable = SpannableString(text)
        val commaIndex = text.indexOf(',')

        if (commaIndex != -1) {
            spannable.setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                ), 0, commaIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannable.setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.grey6
                    )
                ), commaIndex, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        } else {
            spannable.setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                ), 0, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        return spannable
    }

    private fun openMaterialDatePicker(toggle: Boolean) {
        val constraintsBuilder = CalendarConstraints.Builder()
        val initialDateMillis = try {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale("ru"))
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            val parsedDate = sdf.parse(selectedDate)
            parsedDate?.time ?: System.currentTimeMillis()
        } catch (e: ParseException) {
            System.currentTimeMillis()
        }

        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Выберите дату")
            .setCalendarConstraints(constraintsBuilder.build())
            .setTheme(R.style.ThemeOverlay_App_DatePicker)
            .setSelection(initialDateMillis)
            .build()

        datePicker.addOnPositiveButtonClickListener { selection ->
            selectedDate = SimpleDateFormat("yyyy-MM-dd", Locale("ru")).format(Date(selection))
            val formattedDate = SimpleDateFormat("d MMM, E", Locale("ru")).format(Date(selection))

            if (toggle) {
                binding.searchChosenBtnDate.text = styleText(formattedDate)
                viewModel.setSelectedDate(selectedDate)
            }
        }

        datePicker.show(parentFragmentManager, "MATERIAL_DATE_PICKER")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
