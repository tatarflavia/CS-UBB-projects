package com.example.lab2project.repository

import com.example.lab2project.R
import com.example.lab2project.domain.Dress
import kotlin.random.Random

class DressInMemoryRepository {

    private fun getRandomString(length: Int) : String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }
    private fun getRandomInt(min:Int, max:Int): Int{
        return Random.nextInt(min,max)
    }

    fun generateDummyList(size: Int): List<Dress> {
        val list = mutableListOf<Dress>()
        for (i in 0 until size) {
            val photoPath= R.drawable.dress1
            val dressItem = Dress(i+1,getRandomString(10),getRandomString(3),getRandomInt(36,46),getRandomInt(1000,10000),getRandomString(9),getRandomInt(20,50),getRandomString(8),getRandomString(100),photoPath)
            list += dressItem
        }
        return list
    }




}