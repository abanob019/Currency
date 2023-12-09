package com.azmiradi.currency.features.convert_currency.presentation.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.azmiradi.currency.common.exception.BaseException
import com.azmiradi.currency.databinding.ConvertFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ConvertCurrencyFragment : Fragment() {
    private var binding: ConvertFragmentBinding? = null
    private val viewModel: CurrencyConverterViewModel by viewModels()
    private var progressDialog: ProgressDialog? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ConvertFragmentBinding.inflate(inflater, container, false)

        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = ProgressDialog(requireContext())
        observeState()
        setupListeners()
    }

    private fun setupListeners() {
        binding?.convertButton?.setOnClickListener {
            viewModel.getCurrencies()
            viewModel.convertCurrency(
                fromCurrency =binding?.currencyFromSpinner?.selectedItem as String?,
                toCurrency =binding?.currencyToSpinner?.selectedItem as String?,
                amount = binding?.amount?.text?.toString()?.toDoubleOrNull() ?: 1.0
            )
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    handleLoadingState(state.loading)
                    handleErrorState(state.error)
                    handleSuccessState(state)
                }
            }
        }
    }

    private fun handleSuccessState(state: CurrencyConverterState) {
        state.currencySym?.let {
            setupSpinner(it)
        }

        state.convertedRate?.let {
            binding?.convertedValue?.text = it
        }
    }

    private fun handleErrorState(error: BaseException?) {
        error?.let {
            Toast.makeText(requireContext(), it.message?:"", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleLoadingState(loading: Boolean) {
        if (loading) {
            progressDialog?.show()
        } else {
            progressDialog?.dismiss()
        }
    }

    private fun setupSpinner(currencies: List<String>) {
        // Initialize the spinner adapter with the list of currency symbols
        val currencyAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            currencies
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        binding?.currencyFromSpinner?.adapter = currencyAdapter
        binding?.currencyToSpinner?.adapter = currencyAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        progressDialog = null
    }
}