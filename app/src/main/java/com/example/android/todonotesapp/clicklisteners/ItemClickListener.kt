package com.example.android.todonotesapp.clicklisteners

import com.example.android.todonotesapp.db.Notes


interface ItemClickListener {
    fun onClick(notes: Notes)
    fun onUpdate(notes: Notes)
}