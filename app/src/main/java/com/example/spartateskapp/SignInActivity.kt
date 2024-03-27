package com.example.spartateskapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignInActivity : AppCompatActivity() {
    private lateinit var signinButton: Button
    private lateinit var emailAddress: EditText
    private lateinit var passWord: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        signinButton = findViewById(R.id.btn_signin)
        emailAddress = findViewById(R.id.et_emailAddress)
        passWord = findViewById(R.id.et_password)
        signinButton.setOnClickListener {
            chekEachValue()
            gosignUp()
        }
    }

    fun chekEachValue() {
        // 이메일 입력 확인
        if (emailAddress.text.isBlank()) {
            Toast.makeText(this, "이메일을 입력하세요", Toast.LENGTH_SHORT).show()
        } else if (passWord.text.isBlank()) {
            Toast.makeText(this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show()
        } else checkAll()
    }
    fun checkAll() {
        val inputId = emailAddress.text.toString()
        val inputPw = passWord.text.toString()

        val userIdSignIn = intent.getStringExtra("userId")
        val userPwSignIn = intent.getStringExtra("userpassword")
        val userPosition = intent.getStringExtra("userPosition")
        val userName = intent.getStringExtra("userName")
        val intent = Intent(this, HomeActivity::class.java)

        if (inputId == userIdSignIn && inputPw == userPwSignIn) {
            intent.putExtra("Id", inputId)
            intent.putExtra("userPosition", userPosition)
            intent.putExtra("userName", userName)
            startActivity(intent)
        } else {
            Toast.makeText(this, "이메일 혹은 비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
        }
    }

    fun gosignUp() {
        val signUpButton = findViewById<Button>(R.id.btn_signup)
        signUpButton.setOnClickListener {
            val intent2 = Intent(this, SignUpActivity::class.java)
            startActivity(intent2)
        }

    }

}


//인클루드 태그 , 스피너, 드랍다운, 셀렉박스
// 일회성임. 반복문으로 반복해야함
// 아이디 및 비번 리스트에 저장
