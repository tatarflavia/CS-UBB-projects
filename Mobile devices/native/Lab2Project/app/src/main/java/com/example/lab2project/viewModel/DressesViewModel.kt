package com.example.lab2project.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lab2project.R
import com.example.lab2project.domain.Dress
import com.example.lab2project.repository.DressInMemoryRepository

class DressesViewModel:ViewModel(){
    private val dressesList =MutableLiveData<List<Dress>>()
    init {
        val repo=DressInMemoryRepository()
        dressesList.value=repo.generateDummyList(20)
    }

    val dressesLiveData: LiveData<List<Dress>> =dressesList

    fun getDressByID(ID:Int): Dress? {
        for (dress in dressesList.value!!){
            if(dress.dressID==ID)
                return dress
        }
        return null
    }

    fun getSize():Int?{
        return dressesList.value?.size
    }

    fun addDress(dressCode:String,
                 dressName:String,
                 dressSize:String,
                 dressPrice:String,
                 dressColour:String,
                 dressQuantity:String,
                 tailoringTime:String,
                 dressDescription:String){


        val id= dressesList.value?.size?.plus(1)
        val newList= dressesList.value?.toMutableList()
        if(id!=null && newList!=null){
            val dress= Dress(id,dressCode,dressName,dressSize.toInt(),dressPrice.toInt(),dressColour,dressQuantity.toInt(),tailoringTime,dressDescription,
                R.drawable.dress1)
            newList.add(dress)
        }
        dressesList.value=newList

    }


    fun deleteDress(id:Int?){
        if(id!=null){
            val newList= dressesList.value?.toMutableList()
            var k=0
            var firstElem=false
            if (newList != null) {
                for (dress in newList){
                    if(dress.dressID==id){
                        if(k==0)
                            firstElem=true
                        break
                    }
                    k+=1
                }

            if(k!=0 || firstElem){
                newList.removeAt(k)
            }}
            dressesList.value=newList
        }

    }

    fun updateDress(dress:Dress){
        if(dress.dressID!=0){
            val newList= dressesList.value?.toMutableList()
            var k=0
            var firstElem=false
            if (newList != null) {
                for (dressInArray in newList){
                    if(dressInArray.dressID==dress.dressID){
                        if(k==0)
                            firstElem=true
                        break
                    }
                    k+=1
                }
                if(k!=0 || firstElem){
                    newList.set(k,dress)
                }}
            dressesList.value=newList
        }
    }

}