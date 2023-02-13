package com.example.movieapp.custom_dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DeleteDialog(
    val title : String,
    val body : String,
    val positiveButtonText : String,
    val negativeButtonText : String,
    val positiveButtonCallback : () -> Unit
) : DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setRetainInstance(true)
        val builder = AlertDialog.Builder(requireActivity()).apply {
            setTitle(title)
            setMessage(body)
            setPositiveButton(positiveButtonText){dialiogInterface,i ->
                positiveButtonCallback()
            }
            setNegativeButton(negativeButtonText,null)
        }
        return builder.create()
    }
}