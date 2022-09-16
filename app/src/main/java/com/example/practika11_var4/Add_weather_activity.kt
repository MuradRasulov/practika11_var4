package com.example.practika11_var4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Add_weather_activity : AppCompatActivity() {

    private var contactList: MutableList<Weather> = mutableListOf()
    private lateinit var temperature:EditText
    private lateinit var city:EditText
    private lateinit var date:EditText
    private lateinit var month:EditText
    private lateinit var year:EditText
    private lateinit var button:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_weather)
        getContacts()

        temperature = findViewById(R.id.editText_temperature)
        city = findViewById(R.id.editText_city)
        date = findViewById(R.id.editText_date)
        month = findViewById(R.id.editText_month)
        year = findViewById(R.id.editText_year)
        button = findViewById(R.id.add_weather)

        button.setOnClickListener {
            addWeather(temperature.text.toString(), city.text.toString(), date.text.toString(), month.text.toString(), year.text.toString())
            Toast.makeText(this, contactList.toString(), Toast.LENGTH_LONG).show()
            Log.d("Hey", contactList.toString())
        }
    }

    private fun addWeather(temperature:String, city:String, date:String, month:String, year:String) {
        val contact = Weather(temperature, city,date,month,year)
        contactList.add(contact)
        val preferences = getSharedPreferences("pref", MODE_PRIVATE)
        preferences.edit {
            this.putString("json", Gson().toJson(contactList).toString())
        }
    }

    private fun getContacts() {
        val preferences = getSharedPreferences("pref", MODE_PRIVATE)
        var json:String = ""
        if (!preferences.contains("json")){
            return
        }else{
            json = preferences.getString("json", "NOT_JSON").toString()
        }
        val tempList = Gson().fromJson<List<Weather>>(json, object: TypeToken<List<Weather>>(){}.type)
        contactList.addAll(tempList)
    }
    fun back(view: View){
        val intent = Intent(this, MainActivity::class.java).apply {
        }
        startActivity(intent)
    }
}