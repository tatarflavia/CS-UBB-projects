package com.example.lab2project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.lab2project.R
import com.example.lab2project.domain.Dress
import com.example.lab2project.viewModel.DressesViewModel
import kotlinx.android.synthetic.main.fragment_update_dress.*


private const val ARG_PARAM2 = "dressID"

/**
 * A simple [Fragment] subclass.
 * Use the [UpdateDressFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpdateDressFragment : Fragment() {
    private var dressID: Int? = null
    private var selectedDress: Dress? = null
    private val viewModel : DressesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dressID = it.getString(ARG_PARAM2)?.toInt()
            selectedDress=viewModel.getDressByID(dressID!!)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_dress, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dressCode.setText(selectedDress?.dressCode)
        dressName.setText(selectedDress?.dressName)
        dressSize.setText(selectedDress?.dressSize.toString())
        dressPrice.setText(selectedDress?.dressPrice.toString())
        dressQuantity.setText(selectedDress?.dressQuantity.toString())
        dressDesc.setText(selectedDress?.dressDescription)
        dressTailor.setText(selectedDress?.tailoringTime)
        dressColour.setText(selectedDress?.dressColour)


        ButtonCancelUpdate.setOnClickListener{
            it.findNavController().navigate(R.id.action_updateDressFragment_to_dressListFragment)
        }
        ButtonUpdateDress.setOnClickListener{
            val dress=makeUpdatedDressObject()
            viewModel.updateDress(dress)
            it.findNavController().navigate(R.id.action_updateDressFragment_to_dressListFragment)
        }


    }


    private fun makeUpdatedDressObject():Dress{
        val dressCode=dressCode.text.toString()
        val dressName=dressName.text.toString()
        val dressSize=dressSize.text.toString().toInt()
        val dressPrice=dressPrice.text.toString().toInt()
        val dressQuantity=dressQuantity.text.toString().toInt()
        val dressDesc=dressDesc.text.toString()
        val dressTailor=dressTailor.text.toString()
        val dressColour=dressColour.text.toString()
        if(selectedDress?.dressID != null){
            return Dress(selectedDress?.dressID!!,dressCode,dressName,dressSize,dressPrice,dressColour,dressQuantity,dressTailor,dressDesc,R.drawable.dress1)
        }
        return Dress(0,dressCode,dressName,dressSize,dressPrice,dressColour,dressQuantity,dressTailor,dressTailor,R.drawable.dress1)
    }

    companion object {
        fun newInstance() = UpdateDressFragment()
    }
}