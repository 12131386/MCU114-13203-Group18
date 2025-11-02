package com.example.fastfoodwizard

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SideDishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_side_dish)

        val cbFries = findViewById<CheckBox>(R.id.cbFries)
        val cbSalad = findViewById<CheckBox>(R.id.cbSalad)
        val cbNuggets = findViewById<CheckBox>(R.id.cbNuggets)

        findViewById<Button>(R.id.btnSaveSide).setOnClickListener {
            OrderHolder.current.sideDishes.clear()
            if (cbFries.isChecked)  OrderHolder.current.sideDishes.add("薯條")
            if (cbSalad.isChecked)  OrderHolder.current.sideDishes.add("沙拉")
            if (cbNuggets.isChecked) OrderHolder.current.sideDishes.add("雞塊")

            if (OrderHolder.current.sideDishes.isEmpty()) {
                Toast.makeText(this, "請至少選擇一項副餐", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(this, "副餐已選：${OrderHolder.current.sideDishes.joinToString(", ")}", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
