package com.example.chatapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatapplication.databinding.ActivitySplashBinding
import java.io.*

class SplashActivity : AppCompatActivity() {
    lateinit var binding:ActivitySplashBinding
    var start:String = "false"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        readPassword()
        binding.animationView.postDelayed({
            var currentUserID = Firebase().UserId()
            if (currentUserID.isNotEmpty() && start == "true"){
                startActivity(
                    Intent(this,
                    PassCodeActivity::class.java)
                )
            }else if (currentUserID.isNotEmpty() && start == "false"){
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        },1)
    }


    private fun readPassword(){
        try {
            val fileRead = File(cacheDir,"open")
            val fileInputStream = FileInputStream(fileRead)
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder = StringBuilder()
            var line: String? = null
            while (run {
                    line = bufferedReader.readLine()
                    line
                } !=null){
                stringBuilder.append(line)
            }
            fileInputStream.close()
            start = stringBuilder.toString()

        }catch (exp: IOException){
            exp.printStackTrace()
        }
    }



    }
