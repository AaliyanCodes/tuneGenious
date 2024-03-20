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
                        val dialogFragment =loadingFragment()
                        dialogFragment.show(supportFragmentManager, "My Fragment")



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



                                        Log.i("TAG", "onResponse: ${responseBody.byteStream().available()}")

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
                                                if (dialogFragment.isAdded) {
                                                    dialogFragment.dismiss()
                                                }
                                                val intent = Intent(this@Description, GeneratedSong::class.java)


                                                intent.putExtra("audioFilePath", storedFile?.absolutePath)


                                                startActivity(intent)
                                            }


                                    }

                                } else {
                                    if (dialogFragment.isAdded) {
                                        dialogFragment.dismiss()
                                    }
                                    Log.i("TAG", "onResponse: ${response.body()}")
                                }
                            }

                            override fun onFailure(
                                call: Call<ResponseBody>,
                                t: Throwable
                            ) {
                                if (dialogFragment.isAdded) {
                                    dialogFragment.dismiss()
                                }
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

    private fun isOnline(): Boolean {
        val conMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = conMgr.activeNetworkInfo

        if (netInfo == null || !netInfo.isConnected || !netInfo.isAvailable) {
            return false
        }
        return true
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }



}
