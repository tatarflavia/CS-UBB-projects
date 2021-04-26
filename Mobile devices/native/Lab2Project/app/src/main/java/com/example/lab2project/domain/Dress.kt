package com.example.lab2project.domain

class Dress(var dressID:Int,
            var dressCode:String,
            var dressName:String,
            var dressSize:Int,
            var dressPrice:Int,
            var dressColour:String,
            var dressQuantity:Int,
            var tailoringTime:String,
            var dressDescription:String,
            var dressPhoto:Int) {
    override fun toString(): String {
        return "Dress(dressID=$dressID, dressCode='$dressCode', dressName='$dressName', dressSize=$dressSize, dressPrice=$dressPrice, dressColour='$dressColour', dressQuantity=$dressQuantity, tailoringTime='$tailoringTime', dressDescription='$dressDescription', dressPhoto=$dressPhoto)"
    }
}