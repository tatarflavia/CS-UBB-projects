package com.example.lab2project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.lab2project.R
import com.example.lab2project.domain.Dress
import com.example.lab2project.viewModel.DressesViewModel
import kotlinx.android.synthetic.main.fragment_delete_dress.*


private const val ARG_PARAM = "dressID"

/**
 * A simple [Fragment] subclass.
 * Use the [DeleteDressFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DeleteDressFragment : Fragment() {
    private var dressID: Int? = null
    private var selectedDress: Dress?=null
    private val viewModel : DressesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dressID = it.getString(ARG_PARAM)?.toInt()
            selectedDress=viewModel.getDressByID(dressID!!)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete_dress, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DressCodeID.append(selectedDress?.dressCode)
        DressNameID.append(selectedDress?.dressName)

        buttonNo.setOnClickListener{
            it.findNavController().navigate(R.id.action_deleteDressFragment_to_dressListFragment)
        }
        buttonYes.setOnClickListener{
            viewModel.deleteDress(selectedDress?.dressID)
            it.findNavController().navigate(R.id.action_deleteDressFragment_to_dressListFragment)
        }


    }



    companion object {
        fun newInstance() = DeleteDressFragment()
    }
}