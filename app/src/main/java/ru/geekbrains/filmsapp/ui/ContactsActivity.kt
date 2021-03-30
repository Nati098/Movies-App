package ru.geekbrains.filmsapp.ui

import android.Manifest
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import ru.geekbrains.filmsapp.R

class ContactsActivity : AppCompatActivity() {
    private val REQUEST_CODE = 42

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        checkReadPermission()
    }

    private fun checkReadPermission() {
        applicationContext.let {
            when {
                ContextCompat.checkSelfPermission(it, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED -> getContacts()
                shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) ->
                    AlertDialog.Builder(it)
                        .setTitle("Access to contacts")
                        .setPositiveButton("Provide access") { _, _ -> requestPermission() }
                        .setNegativeButton("Don't provide") { dialog, _ -> dialog.dismiss() }
                        .create().show()
                else -> requestPermission()
            }
        }
    }

    private fun getContacts() {
        applicationContext.let {
            val contentResolver: ContentResolver = it.contentResolver
            val cursorContacts: Cursor? = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI, null, null,
                null, ContactsContract.Contacts.DISPLAY_NAME + " ASC")

            cursorContacts?.let { cursor ->
                for (i in 0..cursor.count) {
                    if (cursor.moveToPosition(i)) {
                        val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                        addView(it, name)
                    }
                }
            }
            cursorContacts?.close()
        }
    }

    private fun requestPermission() = requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_CODE)

    private fun addView(context: Context, textToShow: String) {
        //TODO
    }
}