package com.dudek9.adouble


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_summary.*
import java.util.zip.Inflater

class Summary(wynik:Int) : Fragment() {

    private val wynik=wynik

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_summary, container, false)

    }

    override fun onStart() {
        super.onStart()
        SummaryText.setText("Wynik: "+wynik)
    }
}