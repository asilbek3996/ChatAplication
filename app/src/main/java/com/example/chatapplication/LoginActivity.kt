package com.example.chatapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapplication.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword:EditText
    private lateinit var btnLogin:Button
    private lateinit var btnSignUp: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()

//        edtEmail = findViewById(R.id.edt_email)
//        edtPassword = findViewById(R.id.edt_password)
//        btnLogin = findViewById(R.id.btnLogin)
//        btnSignUp = findViewById(R.id.btnSignUp)

        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            var email = binding.edtEmail.text.toString()
            var password = binding.edtPassword.text.toString()
            login(email,password)
        }

    }

    private fun login(email:String,password:String){
        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "email va parolni kiriting", Toast.LENGTH_SHORT).show()
        }else{
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity,"User does not exist",Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener(this) {
                    Toast.makeText(this, "xato", Toast.LENGTH_SHORT).show()
                }
        }


    }
}