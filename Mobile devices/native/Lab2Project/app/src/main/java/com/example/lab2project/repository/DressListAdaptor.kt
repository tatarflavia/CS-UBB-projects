package com.example.lab2project.repository

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2project.domain.Dress
import com.example.lab2project.R
import com.example.lab2project.fragments.DressListFragmentDirections
import kotlinx.android.synthetic.main.list_item.view.*

class DressListAdaptor(private var itemsList:List<Dress>): RecyclerView.Adapter<DressListAdaptor.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        //it's called for the objects on the screen plus a few others off it
        //it's called by the recycler view when it's time to create a new viewHolder
        //we create a view obj for the itemView aka row layout
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item,
        parent,false)
        return ListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        //it's called over and over again(maybe each second), when scrolling, adding data
        val currentItem=itemsList[position]
        holder.imageView.setImageResource(currentItem.dressPhoto)
        holder.textViewForDressCode.text=currentItem.dressCode
        holder.textViewForDressName.text=currentItem.dressName
        holder.bind(currentItem)
    }

    override fun getItemCount()=itemsList.size

    fun updateDressList(repoList: List<Dress>) {
        this.itemsList = repoList
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        //viewholder=a single row in the list, one viewholder instance contains  one instance
        //of list_item.xml row layout and the position about the row handled by the superclass
        //we cache here the elements
        //itemview is one instance of row layout from list_item.xml
        //now we store references to the 3 views in the row layout
        val imageView:ImageView=itemView.iconImageViewID
        val textViewForDressCode:TextView=itemView.textViewDressCodeID
        val textViewForDressName:TextView=itemView.textViewDressNameID
        fun bind(dress:Dress) {
            itemView.setOnClickListener {
                val bundle = bundleOf("dressID" to dress.dressID.toString() )
                itemView.findNavController().navigate(R.id.action_dressListFragment_to_dressDetailsFragment,bundle)
        }
        }

    }
}