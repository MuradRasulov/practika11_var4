package com.example.practika11_var4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Display
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Show_weather_activity : AppCompatActivity() {


    private val contactList: MutableList<Weather> = mutableListOf()
    private lateinit var view1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_weather)

        val message = intent.getStringExtra("EXTRA_MESSAGE")

        //view1.text = message
        //view1 = findViewById<TextView>(R.id.view1)

        Log.d("INFORMS",message.toString())

        getContacts()
        contactList.forEach{
            Log.d("Hey", it.toString())
            view1.text = contactList.toString()
            Toast.makeText(this, "Екб", Toast.LENGTH_SHORT).show()
            Log.d("Hey", contactList.toString())
        }
    }

    private fun getContacts() {
        val preferences = getSharedPreferences("pref", MODE_PRIVATE)
        var json: String = ""
        if (!preferences.contains("json")){
            return
        }else{
            json = preferences.getString("json", "NOT_JSON").toString()
        }
        val tempList = Gson().fromJson<List<Weather>>(json, object : TypeToken<List<Weather>>(){}.type)
    }
    fun back(view: View){
        val intent = Intent(this, MainActivity::class.java).apply {
        }
        startActivity(intent)
    }
}