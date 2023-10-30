package com.ginamelinia.debtlog.debtlog.page.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.ginamelinia.debtlog.R
import com.ginamelinia.debtlog.databinding.FragmentAddDebtBinding
import com.ginamelinia.debtlog.page.fragment.HomeViewModel
import com.ginamelinia.debtlog.repository.local.room.entity.DebtEntity

class AddDebtDialogFragment() : DialogFragment() {

    private lateinit var binding: FragmentAddDebtBinding
    private lateinit var homeViewModel: HomeViewModel
    companion object {
        fun newInstance(homeViewModel: HomeViewModel): AddDebtDialogFragment {
            val fragment = AddDebtDialogFragment()
            fragment.homeViewModel = homeViewModel
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddDebtBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnSubmit.setOnClickListener {
            val name = binding.edtName.text.toString()
            val moneyAmount = binding.edtMoneyAmount.text.toString()

            // Input validation
            if (name.isEmpty() && moneyAmount.isEmpty()) {
                // Error message if both inputs are empty
                Toast.makeText(requireContext(), "Name and money amount cannot be empty!", Toast.LENGTH_SHORT).show()
            } else if (name.isEmpty()) {
                // Error message if name is empty
                Toast.makeText(requireContext(), "Name and money cannot be empty!", Toast.LENGTH_SHORT).show()
            } else if (moneyAmount.isEmpty()) {
                // Error message if the amount of money is empty
                Toast.makeText(requireContext(), "Money amount cannot be empty!", Toast.LENGTH_SHORT).show()
            } else {
                // Add data to the database using the view model
                val newDebt = DebtEntity(id = 0, name = name, moneyAmount = moneyAmount)
                homeViewModel.addDebt(newDebt)

                // Close dialog when debt is added
                dismiss()
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.fragment_add_debt, null)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(view)
        val width = resources.getDimensionPixelSize(R.dimen.dialog_width)
        dialog.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)

        return dialog
    }
}