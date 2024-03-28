package com.example.spartateskapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.spartateskapp.databinding.ActivitySigninBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySigninBinding
    private lateinit var signUpActivityResultLauncher: ActivityResultLauncher<Intent>
    private var id = ""
    private var pw = ""
    private var position = ""
    private var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySigninBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        signUpActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    id = data?.getStringExtra("id") ?: ""
                    pw = data?.getStringExtra("password") ?: ""
                    position = data?.getStringExtra("userPosition") ?: ""
                    name = data?.getStringExtra("userName") ?: ""
                    binding.etEmailAddress.setText(id)
                    binding.etPassword.setText(pw)
                }
            }

        binding.btnSignin.setOnClickListener {
            checkIdAndPassword()
        }
        goSignUp()
    }

    private fun checkIdAndPassword() {
        // 이메일 입력 확인
        if (binding.etEmailAddress.text.isBlank()) {
            Toast.makeText(this, "이메일을 입력하세요", Toast.LENGTH_SHORT).show()
        } else if (binding.etPassword.text.isBlank()) {
            Toast.makeText(this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show()
        } else checkAll()
    }

    private fun checkAll() {
        val inputId = binding.etEmailAddress.text.toString()
        val inputPw = binding.etPassword.text.toString()
//        val userPosition = intent.getStringExtra("userPosition")
//        val userName = intent.getStringExtra("userName")
        val intent = Intent(this, HomeActivity::class.java)
        Log.d("check", "$id,$pw,$inputId,$inputPw")

        if (inputId == id && inputPw == pw) {
            intent.putExtra("id", id)
            intent.putExtra("password", pw)
            intent.putExtra("userPosition", position)
            intent.putExtra("userName", name)
            startActivity(intent)
            Log.d("check2", "$position,$name")
        } else {
            Toast.makeText(this, "이메일 혹은 비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goSignUp() {
        //val signUpButton = findViewById<Button>(R.id.btn_signup)
        binding.btnSignup.setOnClickListener {
            val signUpintent = Intent(this, SignUpActivity::class.java)
            signUpActivityResultLauncher.launch(signUpintent)
        }

    }

    override fun onStop() {
        super.onStop()
        binding.etEmailAddress.text.clear()
        binding.etPassword.text.clear()
    }

}

