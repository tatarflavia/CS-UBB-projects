package com.example.lab2project.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab2project.R
import com.example.lab2project.repository.DressListAdaptor
import com.example.lab2project.viewModel.DressesViewModel
import kotlinx.android.synthetic.main.fragment_dress_list.*


/**
 * A simple [Fragment] subclass.
 * Use the [DressListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DressListFragment : Fragment() {
    private val viewModel :DressesViewModel by activityViewModels()

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dress_list, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addDressButton.setOnClickListener{
            findNavController().navigate(R.id.action_dressListFragment_to_addDressFragment)}

        dressListRecyclerViewID.layoutManager=LinearLayoutManager(this.context)
        val adapterVal = DressListAdaptor(viewModel.dressesLiveData.value ?: emptyList())
        dressListRecyclerViewID.adapter=adapterVal
        viewModel.dressesLiveData.observe(viewLifecycleOwner){
            list->adapterVal.updateDressList(list)
        }



    }



    companion object {
        fun newInstance() = DressListFragment()
    }



}