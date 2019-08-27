package com.dudek9.adouble


import kotlinx.android.synthetic.main.activity_singleplayer.*


class Clock : Runnable {
    var czy:Boolean=true
    val single = Singleplayer.sigleplayer
    override fun run() {
        if(single.card<20) {
            czy = true
            single.clock = 10
            while (single.clock > 0 && czy) {
                Thread.sleep(1000)
                single.clock -= 1
                single.handler.post {
                    single.time.text = "" + single.clock + "s"
                }
            }
            single.handler.post() {
                single.nextCards()
            }
        }
    }

    fun startTimer() {
        Thread(this).start()
    }

    fun stopTimer() {
        czy=false
    }
}