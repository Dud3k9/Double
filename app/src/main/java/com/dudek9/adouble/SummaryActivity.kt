package com.dudek9.adouble

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_summary.*
import kotlinx.android.synthetic.main.content_summary.*

class SummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)
        setSupportActionBar(toolbar)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        text.setText("Wynik: "+intent.getIntExtra("summary",0))

        main.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                var intent:Intent=Intent(this@SummaryActivity,MainActivity::class.java)
                startActivity(intent)
            }

        })
    }

}
