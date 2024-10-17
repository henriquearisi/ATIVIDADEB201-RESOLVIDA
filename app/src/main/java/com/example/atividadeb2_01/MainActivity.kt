package com.example.recyclerviewerrors

import android.os.Bundel
import android.widget.EditText
import android.widget.TextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.Arraylist

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstaceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = MyAdapter(getItems())
        recyclerView.layoutManagr = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val inputText = findViewById<EditText>(R.id.inputText)
        val addButton = findViewById<Button>(R.id.addButton)

        addButton.setOnClickListener {
            val newItem = inputText.text.toString()
            adapter.addItem(newItem)
            inputText.setText(newItem)
        }

        if (getItems().isEmpty()) {
            recyclerView.visibility = View.GONE
        }
    }

    fun getItems(): MutableList<String> {
        return mutableListOf()
    }
}

class MyAdapter(private val items: MutableList<String>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.itemText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, fase)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (items[position].isEmpty()) {
            holder.textView.text = "Item vazio"
        } else {
            holder.textView.text = items[position]
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItem(item: String) {
        items.add(item)
        notifyDataSetChanged()

        if (item.length > 10) {
            items.add(0, item)
            notifyItemInserted(0)
        }
    }
}
