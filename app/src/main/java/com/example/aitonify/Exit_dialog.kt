package com.example.aitonify

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment

class CustomDialog: DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.exit_dialog, container, false)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.rounded_discard_dialog)

        val button1 = view.findViewById<Button>(R.id.dialog1_button2)
        val button = view.findViewById<Button>(R.id.dialog1_button1)
        dialog?.setCancelable(false)

        button.setOnClickListener {
            val a = Intent(Intent.ACTION_MAIN)
            a.addCategory(Intent.CATEGORY_HOME)
            a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(a)
            dismiss()
        }
        button1.setOnClickListener {
            dismiss()
        }
        return view
    }
    override fun onResume() {
        super.onResume()
        val desiredWidth = (resources.displayMetrics.widthPixels * 0.8).toInt()
        dialog?.window?.setLayout(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

}

