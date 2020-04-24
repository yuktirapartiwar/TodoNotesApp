package com.example.android.todonotesapp.clicklisteners

import com.example.android.todonotesapp.model.Notes

interface ItemClickListener {
    fun onClick(notes: Notes)
}