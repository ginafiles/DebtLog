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
import com.ginamelinia.debtlog.databinding.FragmentEditDebtBinding
import com.ginamelinia.debtlog.page.fragment.HomeViewModel
import com.ginamelinia.debtlog.repository.local.room.entity.DebtEntity

class EditDebtDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentEditDebtBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var debtEntity: DebtEntity

    companion object {
        fun newInstance(homeViewModel: HomeViewModel, debtEntity: DebtEntity): EditDebtDialogFragment {
            val fragment = EditDebtDialogFragment()
            fragment.homeViewModel = homeViewModel
            fragment.debtEntity = debtEntity
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditDebtBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.edtName.setText(debtEntity.name)
        binding.edtMoneyAmount.setText(debtEntity.moneyAmount)

        binding.btnSave.setOnClickListener {
            val name = binding.edtName.text.toString()
            val moneyAmount = binding.edtMoneyAmount.text.toString()

            if (name.isEmpty() || moneyAmount.isEmpty()) {
                Toast.makeText(requireContext(), "Nama dan jumlah uang tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else {
                val updatedDebtEntity = DebtEntity(debtEntity.id, name, moneyAmount)
                homeViewModel.updateDebt(updatedDebtEntity)
                dismiss()
            }
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.fragment_edit_debt, null)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(view)
        val width = resources.getDimensionPixelSize(R.dimen.dialog_width)
        dialog.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)

        return dialog
    }
}
