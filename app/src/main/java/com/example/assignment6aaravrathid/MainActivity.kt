package com.example.assignment6aaravrathid


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.DateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener{

    private var expenseList: MutableList<DataClass> = mutableListOf<DataClass>()
    private lateinit var adapter: ExpenseAdapter;
    private lateinit var footerFragment: FooterFragment
    private lateinit var recyclerView: RecyclerView
    private lateinit var expense: EditText
    private lateinit var amount: EditText
    private lateinit var addButton: Button
    private lateinit var detailsButton:Button
    private lateinit var tipButton: Button

    private lateinit var dateButton:Button
    private lateinit var dateView: TextView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview1)
        expense = findViewById(R.id.expense)
        amount = findViewById(R.id.amount)
        addButton = findViewById(R.id.add)
        detailsButton=findViewById(R.id.showDetails)
        tipButton=findViewById(R.id.tip)
        dateButton=findViewById(R.id.dateButton)
        dateView=findViewById(R.id.showDate)


        expenseList = mutableListOf()
        adapter = ExpenseAdapter(expenseList)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter;

       dateButton.setOnClickListener{
            val datePicker=com.example.assignment6aaravrathid.DatePicker()
            datePicker.show(supportFragmentManager,"DATE PICK")
        }
        val datePickerFragment = DatePicker()
        datePickerFragment.show(supportFragmentManager, "datePicker")

        fun addExpense() {
            val name = expense.text.toString().trim()
            val amount = amount.text.toString().trim()

            expenseList.add(DataClass(name, amount,dateView.text.toString()))
            adapter.notifyItemInserted(expenseList.size - 1)


        }



        addButton.setOnClickListener {
            addExpense();
            expense.text.clear()
            amount.text.clear()
            showTotal()

        }

        detailsButton.setOnClickListener {
            if (expenseList.isNotEmpty()) {
                val data = expenseList[0]
                Log.d("MainActivity", "Data to pass: $data")

                val intent = Intent(this, ExpenseDetailActivity::class.java)
                intent.putExtra("DataDetail", data)


                startActivity(intent)
                Log.d("MainActivity", "Starting ExpenseDetailActivity")
            } else {
                Log.d("MainActivity", "Expense list is empty")
            }
        }

        tipButton.setOnClickListener{
            var url =
                "https://www.manulife.ca/personal/plan-and-learn/healthy-finances/financial-planning/ten-simple-money-management-tips.html"
            var intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
        addFooterFragment()
        footerFragment=FooterFragment()
       addHeaderFragment()

    }


    override fun onStart() {
        super.onStart()
        Log.d("ActivityLifecycle", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ActivityLifecycle", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ActivityLifecycle", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ActivityLifecycle", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ActivityLifecycle", "onDestroy called")



    }
    //I tried footer but it does not work. I dont know why
    private fun addHeaderFragment(){
        val headerFragment=HeaderFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentheader,headerFragment)
            .commit()
    }

    private fun addFooterFragment(){
        val footerFragment=FooterFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentfooter, footerFragment)
            .commit()
    }



    override fun onDateSet(p0: android.widget.DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val mCalendar: Calendar = Calendar.getInstance()
        mCalendar.set(Calendar.YEAR, year)
        mCalendar.set(Calendar.MONTH, month)
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        val selectedDate: String =
            DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.getTime())
        dateView?.setText(selectedDate)
    }

    fun showTotal() {
        var total = 0.0
        for (i in expenseList) {
            total += i.amount.toDoubleOrNull() ?:0.0
        }
//        val footer=supportFragmentManager.findFragmentById(R.id.fragmentfooter) as? FooterFragment
//        footer?.getTotal(total)
        footerFragment.getTotal(total)
    }
}