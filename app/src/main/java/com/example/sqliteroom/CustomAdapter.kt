package com.example.sqliteroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val data: List<Bill>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    //REPLACED: mList: List<ItemsViewModel> with data: List<Bill>

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    //REPLACED val ItemsViewModel = mList[position] with
    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val bill = data[position]
        holder.imageView.setImageResource(R.drawable.ic_launcher_foreground) // sets the image to the imageview from our itemHolder class
        holder.textView.text = bill.name
        holder.textView2.text = bill.frequency.toString()
        holder.textView3.text = bill.value.toString()
        holder.textView4.text = bill.dueDayOfMonth.toString() + "th"  // These set the text to the textview from our itemHolder class

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return data.size //REPLACED mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
        val textView2: TextView = itemView.findViewById(R.id.textView2)
        val textView3: TextView = itemView.findViewById(R.id.textView3)
        val textView4: TextView = itemView.findViewById(R.id.textView4)
    }
}
