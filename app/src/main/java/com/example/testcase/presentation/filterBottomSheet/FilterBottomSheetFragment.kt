package com.example.testcase.presentation.filterBottomSheet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testcase.common.OnDataSendListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.testcase.databinding.FragmentFilterBottomSheetBinding

class FilterBottomSheetFragment(private var listener: OnDataSendListener) : BottomSheetDialogFragment() {

    private var _binding: FragmentFilterBottomSheetBinding? = null
    private val binding get() = _binding!!
    private var mBottomSheetListener: OnDataSendListener?=null
    init {
        this.mBottomSheetListener = listener
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        /** attach listener from parent fragment */
        try {
            mBottomSheetListener = context as OnDataSendListener?
        }
        catch (e: ClassCastException){
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       binding.mercedesButton.setOnClickListener {
            listener.onMercedesButtonClicked()
           dismiss()
        }

        binding.fordButton.setOnClickListener {
            listener.onFordButtonClicked()
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}