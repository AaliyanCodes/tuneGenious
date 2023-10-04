package com.example.aitonify

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
    }

    override fun onClick(view: View?) {
        when(view?.id)
        {
            binding.backImageView.id->{
                finish()
            }
        }
    }
}