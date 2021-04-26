package com.example.lab2project.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.lab2project.R
import com.example.lab2project.viewModel.DressesViewModel
import kotlinx.android.synthetic.main.add_dress_fragment.*


class AddDressFragment : Fragment() {



    private val viewModel: DressesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_dress_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ButtonCancelAdd.setOnClickListener{
            view.findNavController().navigate(R.id.action_addDressFragment_to_dressListFragment)
        }
        ButtonSaveDress.setOnClickListener{
            val code = EditTextDressCode.text.toString()
            val name=EditTextDressName.text.toString()
            val size=EditTextDressSize.text.toString()
            val price=EditTextDressPrice.text.toString()
            val colour=EditTextDressColour.text.toString()
            val quantity=EditTextDressQuantity.text.toString()
            val tailor=EditTextDressTailoringTime.text.toString()
            val desc=EditTextDressDesc.text.toString()
            viewModel.addDress(code,name,size,price,colour,quantity,tailor,desc)
            view.findNavController().navigate(R.id.action_addDressFragment_to_dressListFragment)
        }
    }

    companion object {
        fun newInstance() = AddDressFragment()
    }



}