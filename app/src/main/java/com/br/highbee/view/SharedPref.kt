package com.br.highbee.view
import android.content.Context
import android.content.SharedPreferences

class SharedPref(private val context: Context) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("cache", Context.MODE_PRIVATE)
    fun saveCache( key: String, value: String) {

        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun findCache(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun removeCache(key: String){
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }

}
