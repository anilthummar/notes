package com.theapache64.notes.features.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.theapache64.notes.data.MainRepository
import com.theapache64.notes.data.remote.Note
import com.theapache64.notes.utils.calladapter.flow.Resource
import timber.log.Timber

/**
 * Created by theapache64 : Aug 29 Sat,2020 @ 09:59
 */
class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _shouldLoadNotes = MutableLiveData<Boolean>()
    val notes: LiveData<Resource<List<Note>>> = _shouldLoadNotes.switchMap {
        mainRepository.getNames().asLiveData()
    }

    init {
        _shouldLoadNotes.value = true
    }

    fun onAddNameClicked() {
        Timber.d("onAddNameClicked: Add name clicked ")
    }

    fun onRefreshClicked() {
        Timber.d("onRefreshClicked: On refresh clicked")
        _shouldLoadNotes.value = true
    }
}