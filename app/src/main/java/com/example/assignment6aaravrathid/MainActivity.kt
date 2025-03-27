package com.example.assignment6aaravrathid

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var expenseList:MutableList<DataClass> = mutableListOf<DataClass>()
    private lateinit var adapter:ExpenseAdapter;
    private lateinit var recyclerView: RecyclerView
    private lateinit var expense:EditText
    private lateinit var amount:EditText
    private lateinit var addButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView =findViewById(R.id.recyclerview1)
        expense =findViewById(R.id.expense)
        amount =findViewById(R.id.amount)
        addButton=findViewById(R.id.add)


        //expenseList= mutableListOf()
        adapter=ExpenseAdapter(expenseList)

        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter =adapter

        fun addExpense() {
            val name = expense.text.toString().trim()
            val amount = amount.text.toString().trim()

            expenseList.add(DataClass(name, amount))
            adapter.notifyItemInserted(expenseList.size-1)

            expense.text.clear()
            this.amount.text.clear()


        }

        addButton.setOnClickListener{
            addExpense();
        }

    }
}