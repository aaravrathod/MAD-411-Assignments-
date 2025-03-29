package com.example.assignment6aaravrathid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FooterFragment:Fragment() {
     private lateinit var totalTV:TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.footer_fragment, container, false)
        totalTV= view.findViewById(R.id.footerTV);
        return view
    }


    fun getTotal(total: Double) {
        // Make sure the TextView is initialized before using it
        if (::totalTV.isInitialized) {
            totalTV.text = "Total Expenses: $total"
        } else {
            // Handle the case where the TextView has not been initialized yet
            println("totalTV is not initialized yet")
        }
    }

}