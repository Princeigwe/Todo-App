package com.example.todo

import com.example.todo.models.Task

class TaskDataSource {
    companion object{
        fun createTaskData(): ArrayList<Task>{
            val list = ArrayList<Task>()
            list.add(Task("First Task"))
            list.add(Task("Second Task"))
            list.add(Task("Third Task"))
            list.add(Task("Fourth Task"))
            list.add(Task("Fifth Task"))
            list.add(Task("Sixth Task"))
            list.add(Task("Seventh Task"))
            list.add(Task("Eighth Task"))
            list.add(Task("Ninth Task"))
            list.add(Task("Tenth Task"))
            list.add(Task("Eleventh Task"))
            list.add(Task("Twelfth Task"))

            return list
        }
    }
}