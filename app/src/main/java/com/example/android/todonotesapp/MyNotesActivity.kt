package com.example.android.todonotesapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.todonotesapp.adapter.NotesAdapter
import com.example.android.todonotesapp.clicklisteners.ItemClickListener
import com.example.android.todonotesapp.model.Notes
import com.google.android.material.floatingactionbutton.FloatingActionButton

public class MyNotesActivity : AppCompatActivity(){
    var fullName: String? = null
    lateinit var fabAddNotes:FloatingActionButton
    lateinit var sharedPreferences: SharedPreferences
    lateinit var recyclerView: RecyclerView
    val notesList = ArrayList<Notes>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_notes)
        bindViews()
        setupSharedPreferences()
        getIntentData()
        supportActionBar?.title = fullName
        fabAddNotes.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                setupDialogBox()
            }

        })
    }

    private fun setupDialogBox() {
        val view = LayoutInflater.from(this@MyNotesActivity).inflate(R.layout.add_notes_dialog_layout,null)
        val editTextTitle = view.findViewById<EditText>(R.id.editTextTitle)
        val editTextDescription = view.findViewById<EditText>(R.id.editTextDescription)
        val buttonSubmit = view.findViewById<Button>(R.id.buttonSubmit)
        val dialog = AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create()
        buttonSubmit.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val title = editTextTitle.text.toString()
                val description = editTextDescription.text.toString()
                if(title.isNotEmpty() && description.isNotEmpty()){
                    val notes = Notes(title, description)
                    notesList.add(notes)
                }else{
                    Toast.makeText(this@MyNotesActivity, "Title or Description can't be emprt",Toast.LENGTH_SHORT).show()
                }
                setupRecyclerView()
                dialog.hide()
            }
        })
        dialog.show()
    }

    private fun setupRecyclerView() {
        val itemClickListener = object : ItemClickListener{
            override fun onClick(notes: Notes) {
                val intent = Intent(this@MyNotesActivity, DetailActivity::class.java)
                intent.putExtra(AppConstant.TITLE,notes.title)
                intent.putExtra(AppConstant.DESCRIPTION,notes.description)
                startActivity(intent)

            }

        }
        val notesAdapter = NotesAdapter(notesList, itemClickListener)
        val linearLayoutManager = LinearLayoutManager(this@MyNotesActivity)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager=linearLayoutManager
        recyclerView.adapter=notesAdapter

    }

    private fun getIntentData() {
        val intent = intent
        if (intent.hasExtra(AppConstant.FULL_NAME)) {
            fullName = intent.getStringExtra(AppConstant.FULL_NAME)
        }
        if(fullName.isNullOrEmpty()){
            fullName = sharedPreferences.getString(PrefConstant.FULL_NAME, "")
        }


    }

    private fun setupSharedPreferences() {
        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)

    }

    private fun bindViews() {
        fabAddNotes = findViewById(R.id.fabAddNotes)
        recyclerView = findViewById(R.id.recyclerViewNotes)

    }
}