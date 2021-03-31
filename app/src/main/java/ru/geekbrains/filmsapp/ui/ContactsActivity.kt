package ru.geekbrains.filmsapp.ui

import android.Manifest
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import ru.geekbrains.filmsapp.R

class ContactsActivity : AppCompatActivity() {
    private val REQUEST_CODE = 42

    private lateinit var listLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        listLayout = findViewById(R.id.linear_layout_contacts)

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
                        val phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        addView(name, phone)
                    }
                }
            }
            cursorContacts?.close()
        }
    }

    private fun requestPermission() = requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_CODE)

    private fun addView(name: String, phone: String) {
        val card = layoutInflater.inflate(R.layout.card_view_contact, listLayout, false)
        findViewById<TextView>(R.id.text_view_name).text = name
        findViewById<TextView>(R.id.text_view_phone).text = phone

        listLayout.addView(card)
    }
}