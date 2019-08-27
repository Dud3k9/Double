package com.dudek9.adouble


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation
import kotlinx.android.synthetic.main.activity_singleplayer.*

class Singleplayer : AppCompatActivity() {

    var randrArrayCard = RandCard()
    lateinit var mainCardArray: IntArray
    lateinit var secondCardArray: IntArray
    var points = 0
    var card = 1
    var clock = 30
    var handler = Handler()
    lateinit var timer: Clock


    companion object {
        lateinit var sigleplayer: Singleplayer
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singleplayer)
        Singleplayer.sigleplayer = this
        timer = Clock()

        mainCardArray = randrArrayCard.mainCard()
        secondCardArray = randrArrayCard.secondCard()

        setMainCard()
        setSecondCard()
        timer.startTimer()

    }

    fun click(view: View) {

        if (card < 2) {//TODO!!!!!
            if (checkResault(view)) {
                points++
                Points.setText("" + points)
                val anim = ScaleAnimation(1f, 1.5f, 1f, 1.5f)
                anim.duration = 1000
                Points.startAnimation(anim)
                anim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationRepeat(p0: Animation?) {
                    }

                    @SuppressLint("ResourceAsColor")
                    override fun onAnimationEnd(p0: Animation?) {
                        Points.setTextColor(R.color.secondary_text_default_material_light)
                    }

                    override fun onAnimationStart(p0: Animation?) {
                        Points.setTextColor(Color.GREEN)
                    }

                })
            }
            timer.stopTimer()

        }else {
            var intent = Intent()
            intent.putExtra("summary", points)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }


    fun checkResault(view: View): Boolean {
        if (view.contentDescription.equals(randrArrayCard.corect.toString()))
            return true
        return false
    }

    fun setMainCard() {
        image1.setImageResource(resources.getIdentifier("a" + mainCardArray[0], "drawable", packageName))
        image2.setImageResource(resources.getIdentifier("a" + mainCardArray[1], "drawable", packageName))
        image3.setImageResource(resources.getIdentifier("a" + mainCardArray[2], "drawable", packageName))
        image4.setImageResource(resources.getIdentifier("a" + mainCardArray[3], "drawable", packageName))
        image5.setImageResource(resources.getIdentifier("a" + mainCardArray[4], "drawable", packageName))
        image6.setImageResource(resources.getIdentifier("a" + mainCardArray[5], "drawable", packageName))

        image1.rotation= (Math.random()*360).toFloat()
        image2.rotation= (Math.random()*360).toFloat()
        image3.rotation= (Math.random()*360).toFloat()
        image4.rotation= (Math.random()*360).toFloat()
        image5.rotation= (Math.random()*360).toFloat()
        image6.rotation= (Math.random()*360).toFloat()
    }

    fun setSecondCard() {
        image7.setImageResource(resources.getIdentifier("a" + secondCardArray[0], "drawable", packageName))
        image8.setImageResource(resources.getIdentifier("a" + secondCardArray[1], "drawable", packageName))
        image9.setImageResource(resources.getIdentifier("a" + secondCardArray[2], "drawable", packageName))
        image10.setImageResource(resources.getIdentifier("a" + secondCardArray[3], "drawable", packageName))
        image11.setImageResource(resources.getIdentifier("a" + secondCardArray[4], "drawable", packageName))
        image12.setImageResource(resources.getIdentifier("a" + secondCardArray[5], "drawable", packageName))

        image7.rotation= (Math.random()*360).toFloat()
        image8.rotation= (Math.random()*360).toFloat()
        image9.rotation= (Math.random()*360).toFloat()
        image10.rotation= (Math.random()*360).toFloat()
        image11.rotation= (Math.random()*360).toFloat()
        image12.rotation= (Math.random()*360).toFloat()

        image7.contentDescription = secondCardArray[0].toString()
        image8.contentDescription = secondCardArray[1].toString()
        image9.contentDescription = secondCardArray[2].toString()
        image10.contentDescription = secondCardArray[3].toString()
        image11.contentDescription = secondCardArray[4].toString()
        image12.contentDescription = secondCardArray[5].toString()
    }

    fun nextCards() {
        val anim = FlingAnimation(cardView, DynamicAnimation.TRANSLATION_X)
            .setStartVelocity(-4500f).setMinValue(-4000f).setMaxValue(2000f)

        val anim2 = FlingAnimation(cardView2, DynamicAnimation.TRANSLATION_X)
            .setStartVelocity(4500f).setMinValue(-4000f).setMaxValue(2000f)

        anim.addEndListener(object : DynamicAnimation.OnAnimationEndListener {
            override fun onAnimationEnd(
                animation: DynamicAnimation<out DynamicAnimation<*>>?,
                canceled: Boolean,
                value: Float,
                velocity: Float
            ) {

                mainCardArray = randrArrayCard.mainCard()
                secondCardArray = randrArrayCard.secondCard()
                setMainCard()
                setSecondCard()
                timer.startTimer()


                val anim = FlingAnimation(cardView, DynamicAnimation.TRANSLATION_X)
                    .setStartVelocity(4500f).setMinValue(-4000f).setMaxValue(2000f)
                anim.start()

                val anim2 = FlingAnimation(cardView2, DynamicAnimation.TRANSLATION_X)
                    .setStartVelocity(-4500f).setMinValue(-4000f).setMaxValue(2000f)
                anim2.start()
            }

        })

        anim.start()
        anim2.start()

        card++
        Card.setText("" + card + "/20")


    }
}
