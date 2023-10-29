package com.ginamelinia.debtlog.page.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.ginamelinia.debtlog.databinding.FragmentHomeBinding
import com.ginamelinia.debtlog.debtlog.page.fragment.AddDebtDialogFragment
import com.ginamelinia.debtlog.debtlog.page.fragment.EditDebtDialogFragment
import com.ginamelinia.debtlog.page.adapter.DebtAdapter
import com.ginamelinia.debtlog.repository.local.LocalRepository
import com.ginamelinia.debtlog.repository.local.room.database.DebtDatabase
import com.ginamelinia.debtlog.repository.local.room.entity.DebtEntity

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
        homeViewModel = object: ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(
                    LocalRepository(
                        debtDatabase = Room.databaseBuilder(
                            context = requireContext(),
                            name = DebtDatabase.DATABASE_NAME,
                            klass = DebtDatabase::class.java
                        ).build()
                    )
                ) as T
            }
        }.create(HomeViewModel::class.java)
        debtAdapter = DebtAdapter(
            homeViewModel,
            updateDebt = { debtEntity ->
                showEditDebtDialog(debtEntity)
            },
            deleteDebt = { debtEntity ->
                showDeleteConfirmationDialog(debtEntity)
            }
        )
        val recyclerView = binding.recyclerView

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = debtAdapter

        homeViewModel.allDebts.observe(viewLifecycleOwner, { debtList ->
            debtAdapter.submitList(debtList)
        })

        binding.fabInsert.setOnClickListener{
            showAddDebtDialog()
        }
    }

    private fun showAddDebtDialog() {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragment = AddDebtDialogFragment.newInstance(homeViewModel)
        fragment.show(fragmentManager, "AddDebtDialogFragment")
    }

    private fun showEditDebtDialog(debtEntity: DebtEntity) {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragment = EditDebtDialogFragment.newInstance(homeViewModel, debtEntity)
        fragment.show(fragmentManager, "EditDebtDialogFragment")
    }

    private fun showDeleteConfirmationDialog(debtEntity: DebtEntity) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Delete Item")
        alertDialogBuilder.setMessage("Are you sure you want to delete this item?")
        alertDialogBuilder.setPositiveButton("Delete") { _, _ ->
            homeViewModel.deleteDebt(debtEntity)
        }
        alertDialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialogBuilder.create().show()
    }


}