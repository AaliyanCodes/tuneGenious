package com.example.aitonify

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment


class CustomDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_discard, container, false)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.rounded_discard_dialog)

        val button1 = view.findViewById<Button>(R.id.dialog_button2)
        val button = view.findViewById<Button>(R.id.dialog_button1)
        dialog?.setCancelable(false)

        button.setOnClickListener {

            val intent = Intent(activity, Description::class.java)
            startActivity(intent)
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

