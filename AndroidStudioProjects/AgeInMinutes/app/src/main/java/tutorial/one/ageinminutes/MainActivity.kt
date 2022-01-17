package tutorial.one.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var selectedDate : TextView? = null
    private var ageInMinsText : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datePickerBtn : Button = findViewById(R.id.datePicker)
        selectedDate = findViewById(R.id.selectedDate)
        ageInMinsText = findViewById(R.id.ageInMins)
        datePickerBtn.setOnClickListener {
            onDateClick()
        }
    }

    fun onDateClick(){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, {_, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this, "year is $selectedYear, month is ${selectedMonth +1}, day is $selectedDayOfMonth", Toast.LENGTH_LONG).show()
            val selectedDateText : String? = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            selectedDate?.text = selectedDateText

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val dateValue = sdf.parse(selectedDateText)
            val selectedTimeInMins = dateValue.time / 60000
            val currentTime = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentTimeInMins = currentTime.time / 60000
            val ageInMins = currentTimeInMins - selectedTimeInMins
            ageInMinsText?.text = ageInMins.toString()

        },
        year,
        month,
        day).show()


    }
}