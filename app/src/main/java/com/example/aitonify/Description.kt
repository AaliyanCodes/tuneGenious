package com.example.aitonify


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aitonify.databinding.ActivityDescriptionBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import okhttp3.internal.and
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedInputStream
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class Description : AppCompatActivity(), OnClickListener {


    override fun onBackPressed() {

        val dialogFragment = CustomDialog()
        dialogFragment.show(supportFragmentManager, "My Fragment")
    }

    private lateinit var binding: ActivityDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageviewbutton.setBackgroundResource(R.drawable.buttonicon)

        binding.editText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                hideKeyboard()
            }
        }

        binding.root.setOnTouchListener { _, _ ->
            hideKeyboard()
            false
        }



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

        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.imageviewbutton.setBackgroundResource(R.drawable.buttonicon)
            }


            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val text = s.toString()
                val charCount = text.length
                val maxCharCount = 500

                if (charCount > maxCharCount) {
                    val truncatedText = text.substring(0, maxCharCount)
                    binding.editText.setText(truncatedText)
                    binding.editText.setSelection(maxCharCount)
                }
                val displayText = "$charCount/$maxCharCount"
                binding.wordCountTextView.text = displayText
                Log.d("TextWatcher", "Text changed: $s")
                if (s?.isNotEmpty() == true) {
                    binding.imageviewbutton.setBackgroundResource(R.drawable.afterinput)
                    binding.imageViewClear.setImageResource(R.drawable.clear_text)
                    binding.imageViewClear.visibility = View.VISIBLE

                } else {
                    binding.imageviewbutton.setBackgroundResource(R.drawable.buttonicon)
                    binding.imageViewClear.visibility = View.INVISIBLE

                }
            }
        })

    }


    override fun onClick(v: View?) {
        when (v?.id) {
            binding.view1.id -> {
                binding.view1.setBackgroundResource(R.drawable.bg_music_selector)
                binding.view2.setBackgroundResource(android.R.color.transparent)
                binding.view3.setBackgroundResource(android.R.color.transparent)
                binding.view4.setBackgroundResource(android.R.color.transparent)
                binding.view5.setBackgroundResource(android.R.color.transparent)
                binding.view6.setBackgroundResource(android.R.color.transparent)
                binding.view7.setBackgroundResource(android.R.color.transparent)
                binding.view8.setBackgroundResource(android.R.color.transparent)
            }

            binding.view2.id -> {
                binding.view2.setBackgroundResource(R.drawable.bg_music_selector)
                binding.view1.setBackgroundResource(android.R.color.transparent)
                binding.view3.setBackgroundResource(android.R.color.transparent)
                binding.view4.setBackgroundResource(android.R.color.transparent)
                binding.view5.setBackgroundResource(android.R.color.transparent)
                binding.view6.setBackgroundResource(android.R.color.transparent)
                binding.view7.setBackgroundResource(android.R.color.transparent)
                binding.view8.setBackgroundResource(android.R.color.transparent)
            }

            binding.view3.id -> {
                binding.view3.setBackgroundResource(R.drawable.bg_music_selector)
                binding.view1.setBackgroundResource(android.R.color.transparent)
                binding.view2.setBackgroundResource(android.R.color.transparent)
                binding.view4.setBackgroundResource(android.R.color.transparent)
                binding.view5.setBackgroundResource(android.R.color.transparent)
                binding.view6.setBackgroundResource(android.R.color.transparent)
                binding.view7.setBackgroundResource(android.R.color.transparent)
                binding.view8.setBackgroundResource(android.R.color.transparent)
            }

            binding.view4.id -> {
                binding.view4.setBackgroundResource(R.drawable.bg_music_selector)
                binding.view1.setBackgroundResource(android.R.color.transparent)
                binding.view3.setBackgroundResource(android.R.color.transparent)
                binding.view2.setBackgroundResource(android.R.color.transparent)
                binding.view5.setBackgroundResource(android.R.color.transparent)
                binding.view6.setBackgroundResource(android.R.color.transparent)
                binding.view7.setBackgroundResource(android.R.color.transparent)
                binding.view8.setBackgroundResource(android.R.color.transparent)
            }

            binding.view5.id -> {
                binding.view5.setBackgroundResource(R.drawable.bg_music_selector)
                binding.view1.setBackgroundResource(android.R.color.transparent)
                binding.view3.setBackgroundResource(android.R.color.transparent)
                binding.view2.setBackgroundResource(android.R.color.transparent)
                binding.view4.setBackgroundResource(android.R.color.transparent)
                binding.view6.setBackgroundResource(android.R.color.transparent)
                binding.view7.setBackgroundResource(android.R.color.transparent)
                binding.view8.setBackgroundResource(android.R.color.transparent)
            }

            binding.view6.id -> {
                binding.view6.setBackgroundResource(R.drawable.bg_music_selector)
                binding.view1.setBackgroundResource(android.R.color.transparent)
                binding.view3.setBackgroundResource(android.R.color.transparent)
                binding.view2.setBackgroundResource(android.R.color.transparent)
                binding.view4.setBackgroundResource(android.R.color.transparent)
                binding.view5.setBackgroundResource(android.R.color.transparent)
                binding.view7.setBackgroundResource(android.R.color.transparent)
                binding.view8.setBackgroundResource(android.R.color.transparent)
            }

            binding.view7.id -> {
                binding.view7.setBackgroundResource(R.drawable.bg_music_selector)
                binding.view1.setBackgroundResource(android.R.color.transparent)
                binding.view3.setBackgroundResource(android.R.color.transparent)
                binding.view2.setBackgroundResource(android.R.color.transparent)
                binding.view4.setBackgroundResource(android.R.color.transparent)
                binding.view5.setBackgroundResource(android.R.color.transparent)
                binding.view6.setBackgroundResource(android.R.color.transparent)
                binding.view8.setBackgroundResource(android.R.color.transparent)
            }

            binding.view8.id -> {
                binding.view8.setBackgroundResource(R.drawable.bg_music_selector)
                binding.view1.setBackgroundResource(android.R.color.transparent)
                binding.view3.setBackgroundResource(android.R.color.transparent)
                binding.view2.setBackgroundResource(android.R.color.transparent)
                binding.view4.setBackgroundResource(android.R.color.transparent)
                binding.view5.setBackgroundResource(android.R.color.transparent)
                binding.view6.setBackgroundResource(android.R.color.transparent)
                binding.view7.setBackgroundResource(android.R.color.transparent)
            }

            binding.consButton.id -> {

                val retrofit = RetrofitHelper.getInstance()

                if (isOnline()) {
                    val editTextPrompt = findViewById<EditText>(R.id.editText)

                    val userPrompt = editTextPrompt.text.toString()

                    if (userPrompt.isNotEmpty()) {
//                            val progressDialog = ProgressDialog.show(this, "", "Loading...", true)


//                            CoroutineScope(Dispatchers.IO).launch {
                        val key = "U1nnvROG81ntyjbi".toRequestBody("text/plain".toMediaTypeOrNull())
                        val text = userPrompt.toRequestBody("text/plain".toMediaTypeOrNull())

                        val retrofit1 = RetrofitHelper.getInstance()
                        val musicApi = retrofit1.create(MusicApi::class.java)

                        val call = musicApi.getMusicByPrompt(
                            key, text
                        )

                        call.enqueue(object : Callback<ResponseBody> {
                            override fun onResponse(
                                call: Call<ResponseBody>,
                                response: Response<ResponseBody>
                            ) {
                                if (response.isSuccessful) {


                                    response.body()?.let { responseBody ->





//                                        val filePath = File(getExternalFilesDir(null), "audio.wav").absolutePath
//                                        val isFileSaved = saveByteArrayAsWavFile(audioBytes, filePath)

                                        Log.i("TAG", "onResponse: ${responseBody.byteStream().available()}")

//                                        if(responseBody.bytes().isNotEmpty()) {

                                            CoroutineScope(Dispatchers.IO).launch {

                                                val storedFile = async {

                                                    Utils.storeBitmap(
                                                        responseBody.byteStream(),
                                                        this@Description,
                                                        System.currentTimeMillis().toString(),
                                                        true,
                                                        "Audio"
                                                    )
                                                }.await()



                                                Log.i(
                                                    "TAG", "Successful: Bytes Size:" +
                                                            " stored file " + storedFile?.length()
                                                )
                                                // Create an intent to start the GeneratedSong activity
                                                val intent = Intent(this@Description, GeneratedSong::class.java)

                                                // Put the filePath as an extra in the intent
                                                intent.putExtra("audioFilePath", storedFile?.absolutePath)

                                                // Start the GeneratedSong activity
                                                startActivity(intent)
                                            }
//                                        }


//                                        val filePath = File(getExternalFilesDir(null), "generated_audio.wav")
//                                        val isFileSaved = saveResponseBodyAsFile(responseBody, "generated_audio.wav")
                                       /* if (isFileSaved) {
                                            Log.i("TAG", "Successful: Bytes Size: ${audioBytes.size}, WAV file saved at: $filePath")
                                            // Create an intent to start the GeneratedSong activity
                                            val intent = Intent(this@Description, GeneratedSong::class.java)

                                            // Put the filePath as an extra in the intent
                                            intent.putExtra("audioFilePath", filePath)

                                            // Start the GeneratedSong activity
                                            startActivity(intent)
                                        } else {
                                            Log.e("TAG", "Failed to save WAV file")
                                        }*/

                                    }

                                } else {
                                    Log.i("TAG", "onResponse: ${response.body()}")
                                }
                            }

                            override fun onFailure(
                                call: Call<ResponseBody>,
                                t: Throwable
                            ) {
                                Log.e("TAG", "onFailure: ${t.message}")
                            }

                        })

//                            }
                    } else if (userPrompt.isEmpty()) {
                        val text = "add text"
                        val duration = Toast.LENGTH_SHORT
                        val toast = Toast.makeText(applicationContext, text, duration)
                        toast.show()
                    }
                } else {
                    val dialogFragment = ConnectionFragment()
                    dialogFragment.show(supportFragmentManager, "My Fragment")
                }

            }


            binding.image1.id -> {
                startActivity(Intent(this@Description, SettingActivity::class.java))
            }

            binding.imageViewClear.id -> {
                binding.editText.text.clear()
            }


        }


    }
    fun saveResponseBodyAsFile(responseBody: ResponseBody, filename: String): Boolean {
        return try {
            // Use internal storage directory for example; adjust if you're using external storage
            val file = File(getExternalFilesDir(null), filename)

            // Writing the InputStream to a File
            responseBody.byteStream().use { inputStream ->
                FileOutputStream(file).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }

            true // Indicate the file was saved successfully
        } catch (e: IOException) {
            e.printStackTrace()
            false // Indicate the file was not saved successfully
        }
    }

    fun saveByteArrayAsWavFile(audioData: ByteArray, filePath: String): Boolean {
        return try {
            val sampleRate = 44100 // Example, adjust as necessary
            val channels = 1 // Mono
            val byteRate = sampleRate * channels * 16 / 8
            val dataSize = 36 + audioData.size
            val totalDataLen = dataSize + 4

            FileOutputStream(filePath).use { fos ->
                // Write RIFF header
                fos.write("RIFF".toByteArray())
                fos.write(intToByteArray(totalDataLen))
                fos.write("WAVE".toByteArray())

                // Write fmt subchunk
                fos.write("fmt ".toByteArray())
                fos.write(intToByteArray(16)) // Subchunk size (16 for PCM)
                fos.write(shortToByteArray(1)) // Audio format (1 for PCM)
                fos.write(shortToByteArray(channels.toShort()))
                fos.write(intToByteArray(sampleRate))
                fos.write(intToByteArray(byteRate))
                fos.write(shortToByteArray((channels * 16 / 8).toShort())) // Block align
                fos.write(shortToByteArray(16)) // Bits per sample

                // Write data subchunk
                fos.write("data".toByteArray())
                fos.write(intToByteArray(audioData.size))
                fos.write(audioData)
            }
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }



    fun writeWavHeader(outputStream: FileOutputStream, dataSize: Int) {
        val sampleRate = 44100
        val channels = 1
        val bitsPerSample = 16
        val byteRate = sampleRate * channels * bitsPerSample / 8

        // Write WAV header
        outputStream.write("RIFF".toByteArray()) // Chunk ID
        outputStream.write(intToByteArray(36 + dataSize)) // Chunk Size
        outputStream.write("WAVE".toByteArray()) // Format
        outputStream.write("fmt ".toByteArray()) // Subchunk1 ID
        outputStream.write(intToByteArray(16)) // Subchunk1 Size
        outputStream.write(shortToByteArray(1)) // Audio Format (1 for PCM)
//        outputStream.write(shortToByteArray(channels)) // Num Channels
        outputStream.write(intToByteArray(sampleRate)) // Sample Rate
        outputStream.write(intToByteArray(byteRate)) // Byte Rate
        outputStream.write(shortToByteArray((channels * bitsPerSample / 8).toShort())) // Block Align
        outputStream.write(shortToByteArray(bitsPerSample.toShort())) // Bits Per Sample
        outputStream.write("data".toByteArray()) // Subchunk2 ID
        outputStream.write(intToByteArray(dataSize)) // Subchunk2 Size
    }

    fun intToByteArray(value: Int): ByteArray = byteArrayOf(
        (value and 0xFF).toByte(),
        (value shr 8 and 0xFF).toByte(),
        (value shr 16 and 0xFF).toByte(),
        (value shr 24 and 0xFF).toByte()
    )

    fun shortToByteArray(value: Short): ByteArray = byteArrayOf(
        (value and 0xFF).toByte(),
        (value.toInt() shr 8 and 0xFF).toByte()
    )

//    private fun convertToMusic(responseBody: ResponseBody?) {
//       if(responseBody!= null)
//       {
//           val audioData = responseBody.bytes()
//           playWavDate(audioData)
//       }
//    }
//    private fun playWavDate(audioData:ByteArray)
//    {
//        val sampleRate = 44100
//        val channelConfig = AudioFormat.CHANNEL_OUT_MONO
//        val audioFormat = AudioFormat.ENCODING_PCM_16BIT
//        val bufferSize = AudioTrack.getMinBufferSize(sampleRate, channelConfig, audioFormat)
//
//        val audioTrack = AudioTrack(
//            AudioManager.STREAM_MUSIC,
//            sampleRate,
//            channelConfig,
//            audioFormat,
//            bufferSize,
//            AudioTrack.MODE_STREAM
//        )
//        audioTrack.play()
//        audioTrack.write(audioData, 0, audioData.size)
//        audioTrack.stop()
//        audioTrack.release()
//    }


    private fun isOnline(): Boolean {
        val conMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = conMgr.activeNetworkInfo

        if (netInfo == null || !netInfo.isConnected || !netInfo.isAvailable) {
            return false
        }
        return true
    }


    private fun countWords(text: String): Int {
        val words = text.trim().split("\\s+".toRegex())
        return words.size.coerceAtMost(100) // Enforce the 100-word limit
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
//    private fun handleResponse(audioData: ByteArray) {
//        try {
//            saveWavToFile(audioData)
//
//             playWavDate(audioData)
//        } catch (e: IOException) {
//            Log.e(TAG, "IOException while handling response: ${e.message}")
//            // Handle IO exception
//        } catch (e: Exception) {
//            Log.e(TAG, "Exception while handling response: ${e.message}")
//            // Handle other exceptions
//        }
//    }
//
//    private fun saveWavToFile(audioData: ByteArray) {
//        val root = Environment.getExternalStorageDirectory()
//        val dir = File(root.absolutePath + "/new_wav_directory")
//        if (!dir.exists()) {
//            dir.mkdirs()
//        }
//        val file = File(dir, "new_wav_file.wav")
//
//        FileOutputStream(file).use { outputStream ->
//            outputStream.write(audioData)
//            outputStream.flush()
//        }
//    }


}
