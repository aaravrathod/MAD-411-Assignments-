package com.example.assignment6aaravrathid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.Fragment

class DetailsFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_fragment, container, false)

        //retrieve data
        val name=arguments?.getString("Amount")?:"No Amount"
        val amount=arguments?.getString("expense")?:"No Expense"
        val date=arguments?.getString("Date")?:"No Date"


        view.findViewById<TextView>(R.id.amountDetail).text = name
        view.findViewById<TextView>(R.id.expenseDetail).text = amount.toString()
        view.findViewById<TextView>(R.id.dateDetail).text = date
        return view
    }
    }
