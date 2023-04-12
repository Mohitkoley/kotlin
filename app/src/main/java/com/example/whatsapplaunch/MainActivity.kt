package com.example.whatsapplaunch
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.net.toUri
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var num = ""
        if(intent.action == Intent.ACTION_PROCESS_TEXT && intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString().isDigitsOnly()){
            num = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()

        }
        if(num.isDigitsOnly()){
            startWhatsApp(num)
        }else {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
        }

    }

   private fun startWhatsApp(num:String){
        intent = Intent(Intent.ACTION_VIEW)
        intent.setPackage("com.whatsapp")
        val data:String  = if(num[0] == '+'){
            num.substring(1)
        }
        else if(num.length==10){
            "91$num"
        }
        else{
            num
        }
        intent.data = "https://wa.me/$data".toUri()
        startActivity(intent)
    }
}