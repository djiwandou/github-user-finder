package io.gigiperih.githubuser.uitls.ext

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat

fun Activity.hideSoftKeyboard() {
    val imm = ContextCompat.getSystemService(
        this,
        InputMethodManager::class.java
    )
    imm?.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)

}