package com.example.todo

import com.example.todo.models.Note

class NoteDataSource{
    companion object{
        fun createNoteData(): ArrayList<Note>{
            val list = ArrayList<Note>()
            list.add(Note("First Note", "jlkjgljldjgdljlvjldjitknknkvkhkdflljlglkldkgljgldjldf", "08:30"))
            list.add(Note("Second Note", "jlkjgljldjgdljlvjldjitknknkvkhkdflljlglkldkgljgldjldf", "07:30"))
            list.add(Note("Third Note", "jlkjgljldjgdljlvjldjitknknkvkhkdflljlglkldkgljgldjldf", "12:10"))
            list.add(Note("Fourth Note", "jlkjgljldjgdljlvjldjitknknkvkhkdflljlglkldkgljgldjldf", "10:30"))
            list.add(Note("Fifth Note", "jlkjgljldjgdljlvjldjitknknkvkhkdflljlglkldkgljgldjldf", "09:30"))
            list.add(Note("Sixth Note", "jlkjgljldjgdljlvjldjitknknkvkhkdflljlglkldkgljgldjldf", "11:30"))
            list.add(Note("Seventh Note", "jlkjgljldjgdljlvjldjitknknkvkhkdflljlglkldkgljgldjldf", "12:50"))
            return list
        }
    }
}
