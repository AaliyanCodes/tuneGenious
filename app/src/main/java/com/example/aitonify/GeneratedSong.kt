package com.example.aitonify
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import com.example.aitonify.databinding.ActivityGeneratedSongBinding


class GeneratedSong : AppCompatActivity() ,OnClickListener{
    private lateinit var binding : ActivityGeneratedSongBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGeneratedSongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.image1.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when(view?.id) {
            binding.image1.id -> {
               val dialogFragment = CustomDialogFragment()
                dialogFragment.show(supportFragmentManager, "My Fragment")
            }

        }

    }
}