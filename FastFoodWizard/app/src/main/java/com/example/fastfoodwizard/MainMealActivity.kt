package com.example.fastfoodwizard

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainMealActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_meal)

        val rg = findViewById<RadioGroup>(R.id.rgMain)
        findViewById<Button>(R.id.btnSaveMain).setOnClickListener {
            val checkedId = rg.checkedRadioButtonId
            if (checkedId == -1) {
                Toast.makeText(this, "請選擇主餐", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val text = findViewById<RadioButton>(checkedId).text.toString()
            OrderHolder.current.mainMeal = text
            Toast.makeText(this, "主餐已選：$text", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
