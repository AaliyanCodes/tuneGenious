package com.example.aitonify
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.aitonify.databinding.ActivityGeneratedSongBinding


class GeneratedSong : AppCompatActivity() ,OnClickListener{
    private lateinit var binding : ActivityGeneratedSongBinding
    private var isAnimationPlaying = true
    private lateinit var animationView: LottieAnimationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGeneratedSongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.image1.setOnClickListener(this)
        binding.materialButton.setOnClickListener((this))
        animationView = binding.songAnim
        binding.matButton1.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            binding.image1.id -> {
               val dialogFragment = CustomDialogFragment()
                dialogFragment.show(supportFragmentManager, "My Fragment")
            }
            binding.materialButton.id->{
                if (isAnimationPlaying) {
                    animationView.cancelAnimation()
                    isAnimationPlaying = false
                    binding.materialButton.setImageResource(R.drawable.pauseicon)
                } else {
                    animationView.playAnimation()
                    isAnimationPlaying = true
                    binding.materialButton.setImageResource(R.drawable.playicon)
                }
            }
            binding.matButton1.id->{
                shareFile()
            }

        }


    }
    private fun shareFile() {
        val fileUri = Uri.parse("content://path/to/your/file")
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "application/*" // Change the MIME type as needed
        shareIntent.putExtra(Intent.EXTRA_STREAM, fileUri)
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }
}