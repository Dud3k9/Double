package com.dudek9.adouble


import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.getSystemService
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        singleplayer.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                var intent = Intent(this@MainActivity, Singleplayer::class.java)
                startActivityForResult(intent, 1)

            }
        })


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val fm = supportFragmentManager
//            val summary = Summary(data!!.getIntExtra("summary", 0))
//            fm!!.beginTransaction().add(R.id.bg, summary).commit()
            var intent: Intent = Intent(this, SummaryActivity::class.java)
            intent.putExtra("summary", data!!.getIntExtra("summary", 0))
            startActivity(intent)
        }
    }

}
