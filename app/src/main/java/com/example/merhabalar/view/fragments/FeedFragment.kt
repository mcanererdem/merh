package com.example.merhabalar.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.merhabalar.adapter.CountryAdapter
import com.example.merhabalar.databinding.FragmentFeedBinding
import com.example.merhabalar.viewmodel.FeedViewModel

class FeedFragment : Fragment() {
    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!
    private lateinit var feedViewModel: FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        feedViewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        feedViewModel.refreshData()

        binding.countryListRecyclerViewFeedFragment.layoutManager = LinearLayoutManager(context)
        binding.countryListRecyclerViewFeedFragment.adapter = countryAdapter

        binding.swipeRefreshLayoutFeedFragment.setOnRefreshListener {
            binding.countryListRecyclerViewFeedFragment.visibility = View.GONE
            binding.tvErrorFeedFragment.visibility = View.GONE
            binding.progressBarFeedFragment.visibility = View.VISIBLE

            feedViewModel.refreshData()

            binding.swipeRefreshLayoutFeedFragment.isRefreshing = false
        }
        observeLiveData()
    }

    private fun observeLiveData() {
        feedViewModel.countryList.observe(viewLifecycleOwner) {
            it?.let { list ->
                binding.countryListRecyclerViewFeedFragment.visibility = View.VISIBLE
                binding.tvErrorFeedFragment.visibility = View.GONE
                binding.progressBarFeedFragment.visibility = View.GONE

                countryAdapter.updateCountryList(list)
            }
        }

        feedViewModel.countryError.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    binding.tvErrorFeedFragment.visibility = View.VISIBLE
                    binding.countryListRecyclerViewFeedFragment.visibility = View.GONE
                    binding.progressBarFeedFragment.visibility = View.GONE
                } else {
                    binding.tvErrorFeedFragment.visibility = View.GONE
                }
            }
        }

        feedViewModel.countryLoading.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    binding.progressBarFeedFragment.visibility = View.VISIBLE
                    binding.tvErrorFeedFragment.visibility = View.GONE
                    binding.countryListRecyclerViewFeedFragment.visibility = View.GONE
                } else {
                    binding.progressBarFeedFragment.visibility = View.GONE
                }
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        _binding = null
    }
}
