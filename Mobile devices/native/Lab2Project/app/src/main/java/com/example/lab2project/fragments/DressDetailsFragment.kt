package com.example.lab2project.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.lab2project.R
import com.example.lab2project.domain.Dress
import com.example.lab2project.viewModel.DressesViewModel
import kotlinx.android.synthetic.main.fragment_dress_details.*


private const val ARG_PARAM1 = "dressID"

/**
 * A simple [Fragment] subclass.
 * Use the [DressDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DressDetailsFragment : Fragment() {
    private val viewModel : DressesViewModel by activityViewModels()
    private var selectedDress: Dress? = null
    private var param1: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)?.toInt()
            selectedDress=viewModel.getDressByID(param1!!)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dress_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dressColour.append(selectedDress?.dressColour)
        dressSize.append(selectedDress?.dressSize.toString())
        dressPrice.append(selectedDress?.dressPrice.toString())
        dressDesc.append(selectedDress?.dressDescription)
        textViewDressCode.append(selectedDress?.dressCode)
        dressTailor.append(selectedDress?.tailoringTime)
        dressName.append(selectedDress?.dressName)
        dressQuantity.append(selectedDress?.dressQuantity.toString())
        buttonGoBack.setOnClickListener{
            it.findNavController().navigate(R.id.action_dressDetailsFragment_to_dressListFragment)
        }
        buttonDeleteDress.setOnClickListener{
            val bundle = bundleOf("dressID" to selectedDress?.dressID.toString() )
            it.findNavController().navigate(R.id.action_dressDetailsFragment_to_deleteDressFragment,bundle)
        }
        buttonUpdateDress.setOnClickListener{
            val bundle = bundleOf("dressID" to selectedDress?.dressID.toString() )
            it.findNavController().navigate(R.id.action_dressDetailsFragment_to_updateDressFragment,bundle)
        }


    }


    companion object {
        fun newInstance() = DressDetailsFragment()
    }
}