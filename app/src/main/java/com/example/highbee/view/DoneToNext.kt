package com.example.highbee.view
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener

class DoneToNext (private val currentEditText: EditText, private val nextEditText: EditText) : OnEditorActionListener {
    override fun onEditorAction(v: TextView?, actionId: Int, event: android.view.KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            nextEditText.requestFocus()
            return true
        }
        return false
    }
}