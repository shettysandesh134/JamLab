package com.sandeshshetty.jamlab.utils

import android.app.Activity
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.sandeshshetty.jamlab.MainActivity
import com.sandeshshetty.jamlab.business.domain.state.StateMessageCallback
import com.sandeshshetty.jamlab.framework.presentation.UIController
import java.lang.ClassCastException

inline fun SearchView.onQueryTextChanged(crossinline listener: (String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            listener(newText.orEmpty())
            return true
        }
    })
}

fun Activity.displayToast(
    @StringRes message: Int,
//    stateMessageCallback: StateMessageCallback
){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//    stateMessageCallback.removeMessageFromStack()
}

fun Activity.displayToast(
    message: String,
//    stateMessageCallback: StateMessageCallback
){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//    stateMessageCallback.removeMessageFromStack()
}