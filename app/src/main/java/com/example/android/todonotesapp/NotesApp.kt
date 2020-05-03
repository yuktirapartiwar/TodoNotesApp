package com.example.android.todonotesapp

import android.app.Application
import com.example.android.todonotesapp.db.NotesDatabase

class NotesApp : Application(){
    override fun onCreate() {
        super.onCreate()
    }
    fun getNotesDb():NotesDatabase{
        return NotesDatabase.getInstance(this)
    }

}