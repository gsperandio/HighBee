package com.br.highbee.view
import android.content.Context
class SharedPref(private val context: Context) {
    fun saveCache( key: String, value: String) {
        val sharedPreferences = context.getSharedPreferences("cache", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun findCache(key: String): String? {
        val sharedPreferences = context.getSharedPreferences("cache", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, null)
    }
}