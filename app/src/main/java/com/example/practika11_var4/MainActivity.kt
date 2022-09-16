package com.example.practika11_var4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var editText_temperature: EditText
    private lateinit var editText_city: EditText
    private lateinit var editText_date: EditText
    private lateinit var editText_month: EditText
    private lateinit var editText_year: EditText

    private lateinit var button: Button
    private lateinit var button2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*editText_temperature = findViewById(R.id.editText_temperature)

        editText_city = findViewById(R.id.editText_city)
        editText_date = findViewById(R.id.editText_date)
        editText_month = findViewById(R.id.editText_month)
        editText_year = findViewById(R.id.editText_year)*/

        button = findViewById(R.id.add_button)
        button.setOnClickListener{
            val intent:Intent = Intent(this, Add_weather_activity::class.java)
            startActivity(intent)
        }

        button2 = findViewById(R.id.show_button)
        button2.setOnClickListener{
            //val message = "Температура:${temperature} \nГород:${city} \nДата:${date} \nМесяц:${month} \nГод:${year}"
            var message = Weather(editText_temperature.text.toString(), editText_city.text.toString(),editText_date.text.toString(),editText_month.text.toString(),editText_year.text.toString())
            val intent = Intent(this, Show_weather_activity::class.java)
            intent.putExtra("EXTRA_MESSAGE", message.toString())
            startActivity(intent)
        }
    }
}
