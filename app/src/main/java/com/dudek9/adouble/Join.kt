package com.dudek9.adouble

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.android.synthetic.main.activity_join.*
import kotlinx.android.synthetic.main.content_join.*

class Join : AppCompatActivity() {

    var array:Array<String> = arrayOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)
        setSupportActionBar(toolbar)

        var db= FirebaseFirestore.getInstance()
        var doc=db.collection("games").document(intent.getStringExtra("playerName")).set(hashMapOf("player1" to intent.getStringExtra("playerName")))

        db.collection("games").get().addOnSuccessListener{ document ->
            for(i in 0..document.documents.size-1){
                array[i]=document.documents[i].id.toString()
                Log.d("dataa",document.documents[i].id.toString())
            }
        }

        val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,array)
        list.adapter=adapter

    }

}
