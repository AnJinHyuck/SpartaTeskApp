package com.example.spartateskapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userId = findViewById<TextView>(R.id.tv_inputIdInHome)
        val userPosition = findViewById<TextView>(R.id.inputPositionInHome)
        val userName = findViewById<TextView>(R.id.tv_inputNameInHome)

        userId.text = intent.getStringExtra("id")
        userPosition.text = intent.getStringExtra("userPosition")
        userName.text = intent.getStringExtra("userName")
        Log.d("check3", "${userId.text},${userPosition.text},${userName.text}")

        val btn = findViewById<Button>(R.id.btn_goBackInHome)
        btn.setOnClickListener {
            finish()
        }
    }
}