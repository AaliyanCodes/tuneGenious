package com.example.aitonify

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment


class ConnectionFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.connection_dialog, container, false)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.rounded_discard_dialog)

        val button1 = view.findViewById<Button>(R.id.dialog_button2)
        val view1 = view.findViewById<View>(R.id.image_view_dialog1)
        button1.setOnClickListener {
            dismiss()
        }
        view1.setOnClickListener{
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

