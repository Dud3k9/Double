package com.dudek9.adouble

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_waiting.*

class Waiting : AppCompatActivity() {

    var handler = Handler()
    var time = 5
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waiting)


        game.setText("Game" + intent.getStringExtra("game"))
        player1.setText(intent.getStringExtra("player1"))
        player2.setText(intent.getStringExtra("player2"))
        Thread {
            Thread.sleep(1000)
            time--
            handler.post {
                timer.setText(time)
            }
        }
    }


}
