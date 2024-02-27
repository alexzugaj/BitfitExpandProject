package com.codepath.bitfitproject

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.codepath.bitfitproject.databinding.NewSetSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewItemSheet(var exerciseSet: ExerciseSet?) : BottomSheetDialogFragment()
{
    private lateinit var binding: NewSetSheetBinding
    private lateinit var itemViewModel: ItemViewModel
    private var newItemListener: OnNewItemSubmittedListener? = null

    interface OnNewItemSubmittedListener {
        fun onNewItemSubmitted(newItem: ExerciseSet)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if (exerciseSet != null)
        {
            binding.exerciseTitle.text = "Edit Item"
            val editable = Editable.Factory.getInstance()
            binding.exerciseName.text = editable.newEditable(exerciseSet!!.exerciseName)
            binding.reps.text = editable.newEditable(exerciseSet!!.reps.toString())
        }
        else
        {
            binding.exerciseTitle.text = "Add Food"
        }

        itemViewModel = ViewModelProvider(activity).get(ItemViewModel::class.java)
        binding.saveButton.setOnClickListener {
            saveAction()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = NewSetSheetBinding.inflate(inflater,container,false)
        return binding.root
    }


    private fun saveAction() {
        val exerciseName = binding.exerciseName.text.toString()
        val repsStr = binding.reps.text.toString()

        if (exerciseName.isNotBlank() && repsStr.isNotBlank()) {
            val reps = repsStr.toInt() // Parse the calories as an Int
            if (exerciseSet == null) {
                val newSet = ExerciseSet(exerciseName = exerciseName, reps = reps)
                itemViewModel.addWishItem(newSet)
            }
            binding.exerciseName.setText("")
            binding.reps.setText("")
            dismiss()
        }
    }

}