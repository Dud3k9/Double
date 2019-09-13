package com.dudek9.adouble

import android.app.LauncherActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.get
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.android.synthetic.main.activity_join.*
import kotlinx.android.synthetic.main.content_join.*
import kotlinx.android.synthetic.main.content_summary.view.*

class Join : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)
        setSupportActionBar(toolbar)

        var db = FirebaseFirestore.getInstance()
        db.collection("games")
            .document(intent.getStringExtra("playerName"))
            .set(hashMapOf("player1" to intent.getStringExtra("playerName")))


        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, intent.getStringArrayListExtra("array").toArray()
        )
        list.adapter = adapter

        list.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val text = list[p2] as TextView
                db.collection("games")
                    .document(text.text.toString())
                    .update("player2", intent.getStringExtra("playerName"))
                val waitIntent: Intent = Intent(this@Join, Waiting::class.java)
                waitIntent.putExtra("game", text.text.toString())
                waitIntent.putExtra("player1", text.text.toString())
                waitIntent.putExtra("player2", intent.getStringExtra("playerName"))
                startActivity(waitIntent)
                finish()
            }
        })
        Thread {
            while (true) {
                var doc = db.collection("games").document(intent.getStringExtra("playerName")).get()
                    .addOnSuccessListener { doc ->
                        Log.d("dataa", doc.get("player2").toString())
                        if (doc.get("player2") != null) {
                            val waitIntent: Intent = Intent(this@Join, Waiting::class.java)
                            waitIntent.putExtra("game", intent.getStringExtra("playerName"))
                            waitIntent.putExtra("player1", intent.getStringExtra("playerName"))
                            waitIntent.putExtra("player2", doc.get("player1").toString())
                            startActivity(waitIntent)
                            this.finish()
                        }
                    }
                Thread.sleep(100)
            }
        }.start()

    }


}
