package com.mmrbd.note_room_hilt_coroutine.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmrbd.note_room_hilt_coroutine.data.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {
    private var _noteStateFlow: MutableStateFlow<List<Note>> = MutableStateFlow(emptyList())
    val noteStateFlow: StateFlow<List<Note>> = _noteStateFlow;

    fun getAllNote() = viewModelScope.launch {
        noteRepository.getAllNote().collect { data ->
            _noteStateFlow.value = data
        }
    }

    fun inset(note: Note) = viewModelScope.launch {
        noteRepository.insert(note)
        getAllNote()
    }
}