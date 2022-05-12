package com.sandeshshetty.jamlab.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
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

fun Fragment.displayToast(
    message: String,
//    stateMessageCallback: StateMessageCallback
){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//    stateMessageCallback.removeMessageFromStack()
}

fun Fragment.registerForActivity()=
    registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ){permissions->
        if (permissions[Manifest.permission.CAMERA] ?: false) {

        }
        if (permissions[Manifest.permission.RECORD_AUDIO] ?: false) {

        }
    }


// Checking permission is Granted or not
fun String.checkPermission(context: Context) = ContextCompat.checkSelfPermission(
   context,
    this
) == PackageManager.PERMISSION_GRANTED

fun String.isEmailVerified(): Boolean{
    return (!this.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches())
}

fun Activity.setUiController(

): UIController? {
    this.let {
        if (it is MainActivity) {
            try {
               return this as UIController
            } catch (e: ClassCastException) {
                e.printStackTrace()
            }
        }
    }
    return null
}