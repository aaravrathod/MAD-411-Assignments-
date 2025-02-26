package com.example.assignment6aaravrathid



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpenseAdapter(
    private val expenseList: MutableList<DataClass>):
//    private val onDeleteClick:(Int)->Unit) :
    RecyclerView.Adapter<ExpenseAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val expenseTextView: TextView = view.findViewById(R.id.textView2)
        val amountTextView: TextView = view.findViewById(R.id.textView3)
        val deleteButton: Button = view.findViewById(R.id.button)


    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_expense, viewGroup, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val expense = expenseList[position]


        viewHolder.expenseTextView.text = expense.name
        viewHolder.amountTextView.text = expense.amount

        viewHolder.deleteButton.setOnClickListener{
            removeItem(position)
        }






    }


    override fun getItemCount(): Int = expenseList.size

    private fun removeItem(position: Int){
        expenseList.removeAt(position)
        notifyItemRemoved(position)
    }


}
