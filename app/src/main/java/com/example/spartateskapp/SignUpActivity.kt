package com.example.spartateskapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //스피너 구현
        val positionSpinner = findViewById<Spinner>(R.id.sp_position)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.position,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        positionSpinner.adapter = adapter

        //

        val signInBtn = findViewById<Button>(R.id.btn_signup2)
        val signUpName = findViewById<EditText>(R.id.et_signUpName)
        val signUpEmail = findViewById<EditText>(R.id.et_signUpId)
        val signUpPassword = findViewById<EditText>(R.id.et_signUpPassword)

        //유저인포



        signInBtn.setOnClickListener {
            if (signUpName.text.isBlank() || signUpEmail.text.isBlank() || signUpPassword.text.isBlank()) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val userId: String = signUpName.text.toString()
            val userPassword: String = signUpPassword.text.toString()
            val intent = Intent(this, SignInActivity::class.java)
            intent.putExtra("userpassword",userPassword)
            intent.putExtra("userId",userId)

            startActivity(intent)
        }

//        signInBtn.setOnClickListener{
//            val intent2 = Intent(this,HomeActivity::class.java)
//            val userName: String = signUpEmail.text.toString()
//            val userPosition: String = positionSpinner.selectedItem.toString()
//            intent2.putExtra("position",userPosition)
//            intent2.putExtra("username",userName)
//        }

        val gobackInSignUp = findViewById<Button>(R.id.btn_signUpGoback)
        gobackInSignUp.setOnClickListener {
            finish()
        }

    }
}