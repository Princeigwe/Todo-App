package com.example.todo

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import layout.TaskAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [TasksFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TasksFragment : Fragment(), TaskAdapter.OnItemClickListener {
    private val taskAdapter = TaskAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.tasks_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = taskAdapter
        val itemTouchHelper = ItemTouchHelper(taskAdapter.SwipeToDeleteCallback())
        itemTouchHelper.attachToRecyclerView(recyclerView)
        addData()
        super.onViewCreated(view, savedInstanceState)
        val taskFab = view.findViewById<FloatingActionButton>(R.id.task_fab)

        taskFab.setOnClickListener{
            val addTaskDialogView = LayoutInflater.from(activity).inflate(R.layout.add_task_dialog, null)
            val alertDialogBuilder = AlertDialog.Builder(activity)
                .setView(addTaskDialogView)
            val alertDialog = alertDialogBuilder.show()
            addTaskDialogView.findViewById<Button>(R.id.add_task).setOnClickListener{
                val taskInput: EditText = addTaskDialogView.findViewById(R.id.task_input)
                val taskname = taskInput.text.toString()
                taskAdapter.addTaskItem(taskname)
                alertDialog.dismiss()
            }
            addTaskDialogView.findViewById<Button>(R.id.cancel_task).setOnClickListener {
                alertDialog.dismiss()
            }
        }
    }
    private fun addData(){
        val data = TaskDataSource.createTaskData()
        taskAdapter.submitList(data)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TasksFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TasksFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(activity, "you clicked", Toast.LENGTH_LONG).show()
    }
}