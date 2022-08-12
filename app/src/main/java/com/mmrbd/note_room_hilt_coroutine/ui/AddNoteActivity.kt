package com.mmrbd.note_room_hilt_coroutine.ui

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.mmrbd.note_room_hilt_coroutine.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {
    companion object {
        const val TITLE = "title"
        const val DESCRIPTION = "description"
    }

    private lateinit var binding: ActivityAddNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.apply {
            title = "Add Note"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        binding.btnSave.setOnClickListener {
            onBackPressed()
        }

        binding.description.requestFocus()
        binding.description.addTextChangedListener {
            binding.btnSave.isEnabled = it?.length!! > 0
        }
        binding.description.postDelayed(Runnable {
            val inputMethodManager: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(binding.description, InputMethodManager.SHOW_IMPLICIT)
        }, 1000)
    }

    private fun setData() {
        val intent = Intent()
        intent.putExtra(TITLE, binding.title.text.toString())
        intent.putExtra(DESCRIPTION, binding.description.text.toString())
        if (binding.description.text.isNullOrEmpty()) {
            setResult(RESULT_CANCELED)
            return
        }
        setResult(RESULT_OK, intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                Log.d(TAG, "onOptionsItemSelected: BACK CLICK 1")
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        setData()
        super.onBackPressed()
    }


}