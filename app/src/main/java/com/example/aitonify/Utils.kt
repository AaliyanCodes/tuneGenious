package com.example.aitonify

import android.R.id.input
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Environment
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.source
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


object Utils {

    /**
     * save media
     */
    @SuppressLint("LogNotTimber")
    suspend fun storeBitmap(
        stream: InputStream,
        context: Context,
        fileName: String,
        showSave: Boolean? = false,
        folderName: String? = "",
    ): File? {

        return withContext(Dispatchers.IO)
        {
            lateinit var path: String
            lateinit var picDir: File
            var picFile: File? = null
            val state = Environment.getExternalStorageState()

            if (Environment.MEDIA_MOUNTED == state) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    picDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).toString() + "/" + folderName)
                    Log.e("android 11", "storeBitmap: android 11 ")
                } else {
                    path = Environment.getExternalStorageDirectory().toString()
                    picDir = File(path.plus("/$folderName"))
                    Log.e("not android 11", "storeBitmap: not android 11 ")
                }
                if (!picDir.exists()) {
                    picDir.mkdir()
                }

                picFile = File(picDir.path.plus("/${fileName.plus(".wav")}"))
                Log.e("file", "storeBitmap: $picFile")
                // create path to store file
                try {
                    picFile.createNewFile()
                   /* val picOut = FileOutputStream(picFile)

                    FileOutputStream(picFile).use { output ->
                        val buffer = ByteArray(4 * 1024) // or other buffer size
                        var read: Int
                        while (stream.read(buffer).also { read = it } != -1) {
                            output.write(buffer, 0, read)
                        }
                        output.flush()
                    }*/

                    stream.use { inputStream ->
                        FileOutputStream(picFile).use { outputStream ->
                            inputStream.copyTo(outputStream)
                        }
                    }

//                    picOut.close()
                } catch (e: Exception) {
                    Log.e("error", "ScreenShotAndShareIt Error catch : " + e.printStackTrace())
                }
                finally {
                    stream.close()
                }
            }
            return@withContext picFile
        }
    }



}