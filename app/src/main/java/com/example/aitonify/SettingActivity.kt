package com.example.aitonify

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.example.aitonify.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity(), OnClickListener{
    private lateinit var binding : ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backImageView.setOnClickListener(this)
        binding.settingView1.setOnClickListener(this)
        binding.settingView2.setOnClickListener(this)
        binding.settingView6.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id)
        {
            binding.backImageView.id->{
                finish()
            }
            binding.settingView2.id->{
                sendEmail()
            }
            binding.settingView1.id->{

            }
            binding.settingView6.id->{
                finish()
            }
        }
    }
    private fun sendEmail() {
        val recipient = "aaliyanchaudhary8@gmail.com"
        val subject = "Feedback for TuneGenious"
        val body = ""
        val mailToUri = Uri.parse("mailto:$recipient?subject=${Uri.encode(subject)}&body=${Uri.encode(body)}")
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = mailToUri
        }

        val chooser = Intent.createChooser(emailIntent, "Choose an email client")
        if (chooser.resolveActivity(packageManager) != null) {
            startActivity(chooser)
        } else {
            // Handle the situation where no email app is installed.
        }
    }



}