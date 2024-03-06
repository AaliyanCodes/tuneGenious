package com.example.aitonify

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.aitonify.databinding.ActivityGeneratedSongBinding

class GeneratedSong : AppCompatActivity(), View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private lateinit var binding: ActivityGeneratedSongBinding
    private var isAnimationPlaying = false
    private lateinit var animationView: LottieAnimationView
    private lateinit var seekBar: SeekBar
    private var totalTime = 30
    private var updateBy = 1
    private var isRunning = false
    private var mp: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGeneratedSongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        seekBar = binding.seekbar

        binding.materialButton.setImageResource(R.drawable.playicon)
        binding.image1.setOnClickListener(this)
        binding.materialButton.setOnClickListener(this)
        animationView = binding.songAnim
        binding.matButton1.setOnClickListener(this)
        val filePath = intent.getStringExtra("audioFilePath")
        if (filePath != null) {
            // Initialize MediaPlayer with the sound file
             mp = MediaPlayer.create(this, Uri.parse(filePath))

        }
//        val filePath = intent.extras?.get("audioFilePath")
//        val fileUri = Uri.parse(filePath.toString())
//        // Initialize MediaPlayer with the sound file
//        mp = MediaPlayer.create(this, fileUri)
        totalTime = mp?.duration ?: 0

        // Set up the SeekBar and its listener
        seekBar.setOnSeekBarChangeListener(this)

        updateSeekBar()
        animationView.pauseAnimation()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.image1.id -> {
                val dialogFragment = CustomDialogFragment()
                dialogFragment.show(supportFragmentManager, "My Fragment")
            }
            binding.materialButton.id -> {
                if (isAnimationPlaying) {
                    pauseAnimationAndSound()
                } else {
                    startAnimationAndSound()
                }
            }
            binding.matButton1.id -> {
                shareFile()
            }
        }
    }

    private fun startAnimationAndSound() {

        mp?.start()
        animationView.playAnimation()
        isAnimationPlaying = true
        binding.materialButton.setImageResource(R.drawable.pauseicon) // Assuming matButton1 is the button you're referring to
        updateSeekBar()
    }

    private fun pauseAnimationAndSound() {
        mp?.pause()
        animationView.pauseAnimation()
        isAnimationPlaying = false
        binding.materialButton.setImageResource(R.drawable.playicon) // Change to play icon
        isRunning = false
    }

    private fun updateSeekBar() {
        seekBar.max = totalTime
        isRunning = true
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (isRunning) {
                    val currentPosition = mp?.currentPosition ?: 0
                    seekBar.progress = currentPosition
                    handler.postDelayed(this, updateBy.toLong())
                }
            }
        }, updateBy.toLong())
    }

    private fun shareFile() {
        val fileUri = Uri.parse("content:/storage/emulated/0/Android/data/com.example.aitonify/files/audio.wav")
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "application/*"
        shareIntent.putExtra(Intent.EXTRA_STREAM, fileUri)
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }

    override fun onDestroy() {
        super.onDestroy()
        // Release MediaPlayer resources
        mp?.release()
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (fromUser) {
            // Update MediaPlayer position when SeekBar is manually adjusted by the user
            mp?.seekTo(progress)
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        // Stop updating SeekBar while it's being dragged to prevent conflicts
        isRunning = false
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        // Resume updating SeekBar when user releases the thumb
        isRunning = true
        updateSeekBar()
    }
}
