package com.example.fastfoodwizard

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DrinkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink)

        val rg = findViewById<RadioGroup>(R.id.rgDrink)
        findViewById<Button>(R.id.btnSaveDrink).setOnClickListener {
            val id = rg.checkedRadioButtonId
            if (id == -1) {
                Toast.makeText(this, "請選擇飲料", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val text = findViewById<RadioButton>(id).text.toString()
            OrderHolder.current.drink = text
            Toast.makeText(this, "飲料已選：$text", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
