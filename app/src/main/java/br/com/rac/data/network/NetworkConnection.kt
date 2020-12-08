package br.com.rac.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities


class NetworkConnection constructor(private val context: Context) {
    fun isNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (cm != null) {
            val n = cm.activeNetwork

            if (n != null) {
                val nc = cm.getNetworkCapabilities(n)

                if (nc != null) {
                    return nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(
                        NetworkCapabilities.TRANSPORT_WIFI
                    )
                }
            }
        }

        return false
    }
}