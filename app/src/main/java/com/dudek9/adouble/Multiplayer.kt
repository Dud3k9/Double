package com.dudek9.adouble

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.android.synthetic.main.activity_multiplayer.*
import kotlinx.android.synthetic.main.content_multiplayer.*

class Multiplayer : AppCompatActivity() {
    var playername = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiplayer)
        setSupportActionBar(toolbar)

        name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                playername = name.text.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })


        join.setOnClickListener {
            if (!playername.equals("")) {

                var array: ArrayList<String> = arrayListOf<String>()
                var db = FirebaseFirestore.getInstance()
                db.collection("games").get().addOnSuccessListener { document ->
                    for (i in 0..document.documents.size - 1) {
                        array.add(document.documents[i].id)
                    }

                    var intent = Intent(this@Multiplayer, Join::class.java)
                    intent.putExtra("playerName", playername)
                    intent.putExtra("array", array)
                    startActivity(intent)
                }

            } else {
                Toast.makeText(this, "add name", Toast.LENGTH_LONG)
            }
        }
    }

}
