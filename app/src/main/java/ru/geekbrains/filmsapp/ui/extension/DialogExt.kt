package ru.geekbrains.filmsapp.ui.extension

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import ru.geekbrains.filmsapp.R


fun Context.createCancelableAlertDialog(stringId: Int) = AlertDialog.Builder(this)
    .setTitle(stringId)
    .setMessage(stringId)
//    .setIcon(drawableId)
    .setCancelable(true)
    .setPositiveButton(R.string.button_ok, { dialog, id -> })
    .create().show()

fun View.makeLongSnackbar(message: String, actionString: String, action: ((View) -> Unit)?) {
    val s = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        s.setAction(actionString, action)
    } ?: s
}