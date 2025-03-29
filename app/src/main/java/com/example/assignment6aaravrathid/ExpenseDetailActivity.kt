package com.example.assignment6aaravrathid

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class ExpenseDetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.expense_details)

        //get the data from the recycle view
//cuz the Api
        val expenseData = intent.getSerializableExtra("DataDetail") as? DataClass

        val expenseDetail=findViewById<TextView>(R.id.expenseDetail)
        val amountDetail=findViewById<TextView>(R.id.amountDetail)
        val dateDetail=findViewById<TextView>(R.id.dateDetail)


        expenseData?.let {
            expenseDetail.text = it.name
            amountDetail.text = it.amount
            dateDetail.text=it.date
        }
    }
}