package com.example.assignment6aaravrathid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FooterFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.footer_fragment, container, false)
        return view
    }


    fun getTotal(total: Double) {

       var expenses=total
       view?.findViewById<TextView>(R.id.footerfragment)?.text="Total expenses:${expenses}"
    }

}