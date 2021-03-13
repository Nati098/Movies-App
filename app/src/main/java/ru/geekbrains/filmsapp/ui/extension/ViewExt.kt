package ru.geekbrains.filmsapp.ui.extension

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.geekbrains.filmsapp.R
import java.text.SimpleDateFormat
import java.util.*


/* output formatting */
const val DATE_TIME_FORMAT = "dd.MM.yy HH:mm"
fun Date.format() : String = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(this)


/* wandering between Fragments */
inline fun <reified F: Fragment> createFragment(context: Context?, bundle: Bundle?): Fragment =
    F::class.java.newInstance().apply { arguments = bundle }

inline fun <reified F: Fragment> FragmentManager.addToBackStack(context: Context, bundle: Bundle) =
    beginTransaction()
        .add(R.id.fragment, createFragment<F>(context, bundle))
        .commit()
