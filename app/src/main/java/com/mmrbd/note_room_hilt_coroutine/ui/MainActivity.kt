package com.mmrbd.note_room_hilt_coroutine.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mmrbd.note_room_hilt_coroutine.data.models.Note
import com.mmrbd.note_room_hilt_coroutine.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: NoteViewModel by viewModels()

    private lateinit var noteAdapter: NoteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar.apply {
            title = "Note"
        }

        noteAdapter = NoteAdapter(this) {
            Toast.makeText(this, it.title, Toast.LENGTH_LONG).show()
        }

        binding.rcvNote.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = noteAdapter
        }



        viewModel.getAllNote()

        lifecycleScope.launchWhenStarted {
            viewModel.noteStateFlow.collect { notes ->
                noteAdapter.setNoteDataList(notes)
            }
        }

        var resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val title = result.data!!.getStringExtra(AddNoteActivity.TITLE)
                val description = result.data!!.getStringExtra(AddNoteActivity.DESCRIPTION)
                insert(Note(0, title, description))
                println(result.data!!.getStringExtra(AddNoteActivity.DESCRIPTION))
            }
        }

        binding.btnAddNote.setOnClickListener {
            resultLauncher.launch(Intent(this@MainActivity, AddNoteActivity::class.java))
        }
    }

    private fun insert(note: Note) {
        viewModel.inset(note)
    }
}