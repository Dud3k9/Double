package com.dudek9.adouble

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class Conection {

    fun write(){
        val doc1= hashMapOf(
            "name1" to "Maciej")

        var db=FirebaseFirestore.getInstance()
        var doc=db.collection("users").document("adww").update("wdad","wad")
    }

    fun read(){
        var db=FirebaseFirestore.getInstance()
        db.collection("users").document("adww").get().addOnSuccessListener{
                document -> Log.d("dataa", "DocumentSnapshot data: ${document.data}") }
    }
}