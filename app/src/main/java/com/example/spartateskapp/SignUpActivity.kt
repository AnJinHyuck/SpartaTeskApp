package com.example.spartateskapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
    private lateinit var positionSpinner: Spinner
    private lateinit var signInBtn: Button
    private lateinit var signUpName: EditText
    private lateinit var signUpId: EditText
    private lateinit var signUpPassword: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        positionSpinner = findViewById(R.id.sp_position)
        signInBtn = findViewById(R.id.btn_signup2)
        signUpName = findViewById(R.id.et_signUpName)
        signUpId = findViewById(R.id.et_signUpId)
        signUpPassword = findViewById(R.id.et_signUpPassword)

        //스피너 구현
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.position,
            android.R.layout.simple_spinner_item

        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        positionSpinner.adapter = adapter

        makeEmailAddress()
        goBack()

    }
    //

    private fun makeEmailAddress() {
        signInBtn.setOnClickListener {
            if (signUpName.text.isBlank() || signUpId.text.isBlank() || signUpPassword.text.isBlank()) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                val userId: String = signUpId.text.toString()
                val userPassword: String = signUpPassword.text.toString()
                val userName: String = signUpName.text.toString()
                val userPosition: String = positionSpinner.selectedItem.toString()
               // Log.d("checkBeforeIntent","$userId,$userPassword,$userName,$userPosition")

                val resultIntent = Intent().apply {
                    putExtra("id",userId)
                    putExtra("password",userPassword)
                    putExtra("userPosition",userPosition)
                    putExtra("userName",userName)
                }
               // Log.d("checkAfterIntent","$userId,$userPassword,$userName,$userPosition")
                setResult(Activity.RESULT_OK,resultIntent)
                finish()
            }
        }

    }

    private fun goBack() {
        val goBackInSignUp = findViewById<Button>(R.id.btn_signUpGoback)
        goBackInSignUp.setOnClickListener {
            finish()
        }
    }
}
