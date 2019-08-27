package com.dudek9.adouble

class RandCard {

    lateinit var mainCard: IntArray
    var corect:Int = 0

    fun mainCard(): IntArray {
        var tab: IntArray = IntArray(6)
        for (i in 0..5) {
            do {
                tab[i] = ((Math.random() * 11).toInt())
            } while (!check(tab, i))
        }
        mainCard = tab
        return tab
    }

    fun secondCard(): IntArray {
        var tab: IntArray = IntArray(6)
        tab = notmainCard()
        corect=mainCard[(Math.random()*6).toInt()]
        tab[(Math.random()*6).toInt()]=corect
        return tab
    }

    fun notmainCard(): IntArray {
        var tab: IntArray = IntArray(6)
        var ile = 0
        for (i in 0..11) {
            var czy = true
            for (j in 0..mainCard.size-1) {
                if (mainCard[j] == i)
                    czy = false
            }
            if (czy) {
                tab[ile] = i
                ile++
            }
        }
        return tab
    }

    fun check(tab: IntArray, i: Int): Boolean {
        var czy = true
        for (j in 0..tab.size-1) {
            if (tab[i] == tab[j]&&j!=i)
                czy = false
        }
        return czy
    }
}