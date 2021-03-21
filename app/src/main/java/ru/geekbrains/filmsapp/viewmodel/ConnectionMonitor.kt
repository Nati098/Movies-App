package ru.geekbrains.filmsapp.viewmodel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.MutableLiveData
import ru.geekbrains.filmsapp.R

class ConnectionMonitor(private val context: Context) {

    private val isConnected = MutableLiveData<Boolean>()

    private val intentFilter by lazy { IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION) }
    private val networkBroadcastReceiver by lazy {
        object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val isConn = isConnected()
                if (!isConn) Toast.makeText(context, R.string.no_network_message, Toast.LENGTH_LONG).show()
                isConnected.postValue(isConn)
            }
        }
    }

    private val networkCallback by lazy {
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                isConnected.postValue(true)
            }

            override fun onLost(network: Network) {
                isConnected.postValue(false)
                Toast.makeText(context, R.string.no_network_message, Toast.LENGTH_LONG).show()
            }
        }
    }

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getConnectivityManager()?.registerDefaultNetworkCallback(networkCallback)
        }
        else {
            context.registerReceiver(networkBroadcastReceiver, intentFilter)
        }
    }

    private fun getConnectivityManager() = getSystemService(context, ConnectivityManager::class.java)

    private fun isConnected(): Boolean =
        getConnectivityManager()?.activeNetworkInfo?.isConnectedOrConnecting == true

    fun unregisterReceiver() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getConnectivityManager()?.unregisterNetworkCallback(networkCallback)
        }
        else {
            context.unregisterReceiver(networkBroadcastReceiver)
        }
    }
}