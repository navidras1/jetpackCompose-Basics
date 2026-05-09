package com.hfad.marketsuperapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    val textFiedState =  MutableLiveData("");
    fun onTextChange(newText : String){
        textFiedState.value = newText
    }
}