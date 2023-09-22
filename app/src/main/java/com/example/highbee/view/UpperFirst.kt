package com.example.highbee.view
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class UpperFirst(private val editText: EditText) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        if (!s.isNullOrEmpty() && s.length == 1 && Character.isLowerCase(s[0])) {
            val capitalizedText = s.toString().replaceFirstChar { it.uppercase() }
            editText.removeTextChangedListener(this)
            editText.setText(capitalizedText)
            editText.setSelection(capitalizedText.length)
            editText.addTextChangedListener(this)
        }
    }
}