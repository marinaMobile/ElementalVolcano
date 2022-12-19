package com.sunsetgames.bordersecurit.second

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.sunsetgames.bordersecurit.databinding.ZeroAlertBinding

object AlertSecGm {
    fun showdialog(context: Context){
        var dialog : AlertDialog? = null
        val builder = AlertDialog.Builder(context)
        val binding = ZeroAlertBinding.inflate(LayoutInflater.from(context))
        builder.setView(binding.root)

        dialog = builder.create()
        dialog.window?.setBackgroundDrawable(null)
        dialog.show()

    }
}