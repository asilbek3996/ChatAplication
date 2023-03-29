package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatapplication.databinding.ActivityPassCodeBinding
import com.hanks.passcodeview.PasscodeView.PasscodeViewListener
import java.io.*

class PassCodeActivity : AppCompatActivity() {
    lateinit var binding:ActivityPassCodeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPassCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        readPassword()
        var passcode = binding.textPasscode.text
        binding.passcodeView.setPasscodeLength(4)
            .setLocalPasscode("$passcode").listener = object : PasscodeViewListener{
            override fun onFail() {

            }

            override fun onSuccess(number: String?) {
                val intent = Intent(this@PassCodeActivity,MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }

    }
    private fun readPassword(){
        try {
            val fileRead = File(cacheDir,"passcode")
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
            binding.textPasscode.text = stringBuilder

        }catch (exp: IOException){
            exp.printStackTrace()
        }
    }

}