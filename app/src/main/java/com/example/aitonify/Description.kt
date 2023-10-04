package com.example.aitonify

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.blue
import com.example.aitonify.databinding.ActivityDescriptionBinding

class Description : AppCompatActivity(),OnClickListener {

    private lateinit var binding:ActivityDescriptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view1.setOnClickListener(this)
        binding.view2.setOnClickListener(this)
        binding.view3.setOnClickListener(this)
        binding.view4.setOnClickListener(this)
        binding.view5.setOnClickListener(this)
        binding.view6.setOnClickListener(this)
        binding.view7.setOnClickListener(this)
        binding.view8.setOnClickListener(this)
        binding.consButton.setOnClickListener(this)
        binding.image1.setOnClickListener(this)
        binding.imageViewClear.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            binding.view1.id->{
                binding.view1.setBackgroundResource(R.drawable.bg_music_selector)
                binding.view2.setBackgroundResource(android.R.color.transparent)
                binding.view3.setBackgroundResource(android.R.color.transparent)
                binding.view4.setBackgroundResource(android.R.color.transparent)
                binding.view5.setBackgroundResource(android.R.color.transparent)
                binding.view6.setBackgroundResource(android.R.color.transparent)
                binding.view7.setBackgroundResource(android.R.color.transparent)
                binding.view8.setBackgroundResource(android.R.color.transparent)
            }

            binding.view2.id->{
                binding.view2.setBackgroundResource(R.drawable.bg_music_selector)
                binding.view1.setBackgroundResource(android.R.color.transparent)
                binding.view3.setBackgroundResource(android.R.color.transparent)
                binding.view4.setBackgroundResource(android.R.color.transparent)
                binding.view5.setBackgroundResource(android.R.color.transparent)
                binding.view6.setBackgroundResource(android.R.color.transparent)
                binding.view7.setBackgroundResource(android.R.color.transparent)
                binding.view8.setBackgroundResource(android.R.color.transparent)
            }

            binding.view3.id->{
                binding.view3.setBackgroundResource(R.drawable.bg_music_selector)
                binding.view1.setBackgroundResource(android.R.color.transparent)
                binding.view2.setBackgroundResource(android.R.color.transparent)
                binding.view4.setBackgroundResource(android.R.color.transparent)
                binding.view5.setBackgroundResource(android.R.color.transparent)
                binding.view6.setBackgroundResource(android.R.color.transparent)
                binding.view7.setBackgroundResource(android.R.color.transparent)
                binding.view8.setBackgroundResource(android.R.color.transparent)
            }
            binding.view4.id->{
                binding.view4.setBackgroundResource(R.drawable.bg_music_selector)
                binding.view1.setBackgroundResource(android.R.color.transparent)
                binding.view3.setBackgroundResource(android.R.color.transparent)
                binding.view2.setBackgroundResource(android.R.color.transparent)
                binding.view5.setBackgroundResource(android.R.color.transparent)
                binding.view6.setBackgroundResource(android.R.color.transparent)
                binding.view7.setBackgroundResource(android.R.color.transparent)
                binding.view8.setBackgroundResource(android.R.color.transparent)
            }
            binding.view5.id->{
                binding.view5.setBackgroundResource(R.drawable.bg_music_selector)
                binding.view1.setBackgroundResource(android.R.color.transparent)
                binding.view3.setBackgroundResource(android.R.color.transparent)
                binding.view2.setBackgroundResource(android.R.color.transparent)
                binding.view4.setBackgroundResource(android.R.color.transparent)
                binding.view6.setBackgroundResource(android.R.color.transparent)
                binding.view7.setBackgroundResource(android.R.color.transparent)
                binding.view8.setBackgroundResource(android.R.color.transparent)
            }
            binding.view6.id->{
                binding.view6.setBackgroundResource(R.drawable.bg_music_selector)
                binding.view1.setBackgroundResource(android.R.color.transparent)
                binding.view3.setBackgroundResource(android.R.color.transparent)
                binding.view2.setBackgroundResource(android.R.color.transparent)
                binding.view4.setBackgroundResource(android.R.color.transparent)
                binding.view5.setBackgroundResource(android.R.color.transparent)
                binding.view7.setBackgroundResource(android.R.color.transparent)
                binding.view8.setBackgroundResource(android.R.color.transparent)
            }
            binding.view7.id->{
                binding.view7.setBackgroundResource(R.drawable.bg_music_selector)
                binding.view1.setBackgroundResource(android.R.color.transparent)
                binding.view3.setBackgroundResource(android.R.color.transparent)
                binding.view2.setBackgroundResource(android.R.color.transparent)
                binding.view4.setBackgroundResource(android.R.color.transparent)
                binding.view5.setBackgroundResource(android.R.color.transparent)
                binding.view6.setBackgroundResource(android.R.color.transparent)
                binding.view8.setBackgroundResource(android.R.color.transparent)
            }
            binding.view8.id->{
                binding.view8.setBackgroundResource(R.drawable.bg_music_selector)
                binding.view1.setBackgroundResource(android.R.color.transparent)
                binding.view3.setBackgroundResource(android.R.color.transparent)
                binding.view2.setBackgroundResource(android.R.color.transparent)
                binding.view4.setBackgroundResource(android.R.color.transparent)
                binding.view5.setBackgroundResource(android.R.color.transparent)
                binding.view6.setBackgroundResource(android.R.color.transparent)
                binding.view7.setBackgroundResource(android.R.color.transparent)
            }
            binding.consButton.id->{
                if(isOnline())
                   {
                       startActivity(Intent(this@Description, GeneratedSong::class.java))
                   }
                    else
                   {
                       val dialogFragment = ConnectionFragment()
                       dialogFragment.show(supportFragmentManager, "My Fragment")
                   }
                }
            binding.image1.id->{
                startActivity(Intent(this@Description, SettingActivity::class.java))
            }
            binding.imageViewClear.id->{
               binding.editText.text.clear()
            }


        }

    }
    private fun isOnline(): Boolean {
        val conMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = conMgr.activeNetworkInfo

        if (netInfo == null || !netInfo.isConnected || !netInfo.isAvailable) {
            return false
        }
        return true
    }

}