package com.example.fastfoodwizard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView

    // 用新的 Activity Result API 接收 ConfirmActivity 的回傳
    private val confirmLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val summary = result.data?.getStringExtra("orderSummary")
        if (!summary.isNullOrBlank()) {
            tvResult.text = summary
            Toast.makeText(this, "已提交訂單！", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)

        findViewById<Button>(R.id.btnMainMeal).setOnClickListener {
            startActivity(Intent(this, MainMealActivity::class.java))
        }
        findViewById<Button>(R.id.btnSideDish).setOnClickListener {
            startActivity(Intent(this, SideDishActivity::class.java))
        }
        findViewById<Button>(R.id.btnDrink).setOnClickListener {
            startActivity(Intent(this, DrinkActivity::class.java))
        }
        findViewById<Button>(R.id.btnConfirm).setOnClickListener {
            confirmLauncher.launch(Intent(this, ConfirmActivity::class.java))
        }
        findViewById<Button>(R.id.btnReset).setOnClickListener {
            OrderHolder.reset()
            tvResult.text = "（已清空，尚未提交）"
            Toast.makeText(this, "已清空選擇", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        // 回到主畫面時，同步預覽目前選擇（尚未提交）
        tvResult.text = OrderHolder.summary()
    }
}
