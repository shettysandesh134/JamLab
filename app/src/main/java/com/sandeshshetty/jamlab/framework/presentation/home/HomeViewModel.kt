package com.sandeshshetty.jamlab.framework.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandeshshetty.jamlab.business.data.preferences.abstraction.DataStoreRepository
import com.sandeshshetty.jamlab.business.data.preferences.util.NAME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val repository: DataStoreRepository
): ViewModel() {

    fun changeName(value: String) {
        viewModelScope.launch {
            repository.putString(NAME, value)
        }
    }

    fun getData(key: String): String?  {
        var value: String? = "d"
        viewModelScope.launch {
            value = repository.getString(key)
        }
        return value
    }


    init {

    }


}