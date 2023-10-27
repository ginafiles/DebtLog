package com.ginamelinia.debtlog.page.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ginamelinia.debtlog.databinding.FragmentHomeBinding
import com.ginamelinia.debtlog.page.adapter.DebtAdapter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var debtAdapter: DebtAdapter
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        debtAdapter = DebtAdapter(homeViewModel)

        val recyclerView = binding.recyclerView

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = debtAdapter

        homeViewModel.debts.observe(viewLifecycleOwner, { debtList ->
            debtAdapter.submitList(debtList)
        })

        binding.fabInsert.setOnClickListener{
            homeViewModel.createDebt()
        }
    }
}