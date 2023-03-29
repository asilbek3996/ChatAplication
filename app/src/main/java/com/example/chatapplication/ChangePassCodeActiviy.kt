package com.example.chatapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_change_pass_code_activiy.*
import org.greenrobot.eventbus.EventBus
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ChangePassCodeActiviy : AppCompatActivity() {
    var strData: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pass_code_activiy)

      cvNextCreate.setOnClickListener {
            passwordTV.text = etChangePassword.text.toString()
            llCreate.visibility = View.GONE
            llConfirm.visibility = View.VISIBLE
        }
        cvNextConfirm.setOnClickListener {
            if (etChangePasswordConfirm.text.toString() == passwordTV.text){
                savePassword()
                passcode()
                var text = PassCodeModel(resources.getString(R.string.kirish_paroli_on),"true")
                EventBus.getDefault().post(text)
                var truetxt = "true"
                val intent2 = Intent()
                intent2.putExtra("true",truetxt)
                setResult(Activity.RESULT_OK,intent2)
                finish()
            }
            else{
                Toast.makeText(this, "xato", Toast.LENGTH_LONG).show()
            }
        }

    }
    private fun savePassword(){
        try {
            val fileSave = File(cacheDir,"passcode")
            val fileOutputStream = FileOutputStream(fileSave)
            strData = etChangePasswordConfirm.text.toString()
            fileOutputStream.write(strData.toByteArray())
            fileOutputStream.close()
        }catch (exp: IOException){
            exp.printStackTrace()
        }
    }
    private fun passcode(){
        try {
            val fileSave = File(cacheDir,"open")
            val fileOutputStream = FileOutputStream(fileSave)
            strData = "true"
            fileOutputStream.write(strData.toByteArray())
            fileOutputStream.close()
            finish()
        }catch (exp: IOException){
            exp.printStackTrace()
        }
    }

}