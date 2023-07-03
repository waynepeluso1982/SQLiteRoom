package com.example.sqliteroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //RECYCLER VIEW
        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerview.layoutManager = LinearLayoutManager(this) // this creates a vertical layout Manager
        //OPTIONS: LinearLayoutManager or GridLayoutManager (requires import)

        val data = ArrayList<ItemsViewModel>() // ArrayList of class ItemsViewModel

        for (bill in bills){
            data.add(ItemsViewModel(R.drawable.ic_launcher_foreground, bill.name, bill.frequency, bill.value, bill.dueDayOfMonth))
        }

        val adapter = CustomAdapter(bills) // This will pass the ArrayList to our Adapter

        recyclerview.adapter = adapter // Setting the Adapter with the recyclerview

    }

    val income = listOf(
        Income("ADF", Frequency.FORTNIGHTLY, 2117.65),
        Income("Pension", Frequency.FORTNIGHTLY, 6.75)
    )

    private val bills = listOf(
        Bill("Internet", Frequency.MONTHLY, 79.99, 29),
        Bill("Child Support", Frequency.MONTHLY, 2520.42, 8),
        Bill("Mobile Phone", Frequency.MONTHLY, 49.00, 16)
    )
}



class Income (val name: String, val frequency: Frequency, val value: Double): Serializable {
    fun percentageAllocation (total: Double, value: Double): Double {
        return (value / total) * 100
    }
    fun percentageOf (total: Double, percentage: Double): Double {
        return (total * percentage) / 100
    }
}

class Bill(var name: String, var frequency: Frequency, var value: Double, var dueDayOfMonth: Int) {
    companion object {
        fun calculateValue(frequency: Frequency, billValue: Double): Double {
            return when (frequency) {
                Frequency.MONTHLY -> billValue / 2 //TODO Decision!: Do I calculate this as correct figure value * 12 / 26???
                Frequency.FORTNIGHTLY -> billValue
                Frequency.WEEKLY -> billValue * 2
                else -> 0.0
            }
        }
    }
}

enum class Frequency(valDisplayName:String){
    DAILY("Daily"),
    WEEKLY("Weekly"),
    FORTNIGHTLY("Fortnightly"),
    MONTHLY("Monthly"),
    QUARTERLY("Quarterly"),
    ANNUALLY("Annually")
}