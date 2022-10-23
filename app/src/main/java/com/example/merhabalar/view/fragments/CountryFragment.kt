package com.example.merhabalar.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import com.example.merhabalar.databinding.FragmentCountryBinding
import com.example.merhabalar.model.Country
import com.example.merhabalar.viewmodel.CountryViewModel
import com.example.merhabalar.viewmodel.FeedViewModel

class CountryFragment : Fragment() {

    private var _binding: FragmentCountryBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CountryViewModel
    private val feedViewModel: FeedViewModel by viewModels()

    private var countryUUID = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom()
        observeLiveData()

        feedViewModel.countryError.value = true
    }

    private fun observeLiveData() {
        viewModel.country.observe(viewLifecycleOwner) {
            it?.let { country ->
                binding.tvNameCountryFragment.text = country.name
                binding.tvCapitalCountryFragment.text = country.capital
                binding.tvRegionCountryFragment.text = country.region
                binding.tvCurrencyCountryFragment.text = country.currency
                binding.tvLanguageCountryFragment.text = country.language
                countryUUID = country.uuid
            }
        }
    }
}
