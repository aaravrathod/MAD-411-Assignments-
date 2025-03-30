package com.example.assignment6aaravrathid

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

class MainFragment : Fragment() {

    private val FILE_NAME = "Expenses.json"
    private var expenseList: MutableList<DataClass> = mutableListOf<DataClass>()
    private lateinit var adapter: ExpenseAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var expense: EditText
    private lateinit var amount: EditText
    private lateinit var addButton: Button
    private lateinit var detailsButton: Button
    private lateinit var dateView: TextView  // Initialize the dateView here

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.main_fragment, container, false)


        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        expense = view.findViewById(R.id.expense)
        amount = view.findViewById(R.id.amount)
        addButton = view.findViewById(R.id.add)
        detailsButton = view.findViewById(R.id.showDetails)
        dateView = view.findViewById(R.id.showDate)


        expenseList = loadExpensesFromFile(requireContext())


        adapter = ExpenseAdapter(expenseList)
        recyclerView.adapter = adapter


        addButton.setOnClickListener {
            addExpense()
        }

        return view
    }

    fun addExpense() {
        val name = expense.text.toString().trim()
        val amountText = amount.text.toString().trim()

        if (name.isNotEmpty() && amountText.isNotEmpty()) {

            val newExpense = DataClass(name, amountText, dateView.text.toString())


            expenseList.add(newExpense)


            adapter.notifyItemInserted(expenseList.size - 1)


            saveExpensesToFile(requireContext(), expenseList)


            expense.text.clear()
            amount.text.clear()
        }
    }

    fun loadExpensesFromFile(context: Context): MutableList<DataClass> {
        val taskList: MutableList<DataClass> = mutableListOf()
        try {
            val file = File(context.filesDir, FILE_NAME)
            if (!file.exists()) return taskList

            val json = file.readText()
            val type = object : TypeToken<List<DataClass>>() {}.type
            val loadedTasks: List<DataClass> = Gson().fromJson(json, type)
            taskList.addAll(loadedTasks)

            Log.d("FileStorage", "Tasks loaded successfully")
        } catch (e: FileNotFoundException) {
            Log.e("FileStorage", "File not found: ${e.message}")
        } catch (e: IOException) {
            Log.e("FileStorage", "Error reading file: ${e.message}")
        }
        return taskList
    }


    fun saveExpensesToFile(context: Context, taskList: List<DataClass>) {
        try {
            val json = Gson().toJson(taskList)
            context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use { output ->
                output.write(json.toByteArray())
            }
            Log.d("FileStorage", "Tasks saved successfully")
        } catch (e: IOException) {
            Log.e("FileStorage", "Error saving tasks: ${e.message}")
        }
    }
}
