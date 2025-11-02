package com.example.fastfoodwizard

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ConfirmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm)

        val tvPreview = findViewById<TextView>(R.id.tvPreview)

        val order = OrderHolder.current
        if (!order.isComplete()) {
            Toast.makeText(this, "請選擇主餐、副餐、飲料後再確認", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        val message = """
            主餐：${order.mainMeal}
            副餐：${order.sideDishes.joinToString(", ")}
            飲料：${order.drink}
            
            要提交這份訂單嗎？
        """.trimIndent()

        tvPreview.text = message

        AlertDialog.Builder(this)
            .setTitle("提交訂單")
            .setMessage(message)
            .setPositiveButton("Submit") { d, _ ->
                d.dismiss()
                val summary = OrderHolder.summary()
                val data = Intent().putExtra("orderSummary", summary)
                setResult(RESULT_OK, data)
                Toast.makeText(this, "Order submitted!", Toast.LENGTH_SHORT).show()
                finish()
            }
            .setNegativeButton("Cancel") { d, _ ->
                d.dismiss()
                finish()
            }
            .setCancelable(false)
            .show()
    }
}
