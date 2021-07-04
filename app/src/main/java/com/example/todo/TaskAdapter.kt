package layout

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.models.Task
import java.util.ArrayList

class TaskAdapter(val listener: OnItemClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var taskItems: MutableList<Task> = ArrayList()

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder =  LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is TaskViewHolder -> {
                holder.bindData(taskItems[position])
                holder.checked()
            }
        }
    }

    override fun getItemCount(): Int {
        return taskItems.size
    }
    fun submitList(taskList: MutableList<Task>){
        taskItems = taskList
    }
    fun deleteTaskItem(i: Int){
        taskItems.removeAt(i)
        notifyItemChanged(i)
    }

   inner class TaskViewHolder(itemView: View):RecyclerView.ViewHolder(itemView), View.OnClickListener{
        private var taskName: TextView = itemView.findViewById(R.id.task_name)
        private var taskCheckBox: CheckBox = itemView.findViewById(R.id.task_checkbox)

       init {
           itemView.setOnClickListener(this)
       }

       override fun onClick(p0: View?) {
           val position: Int = adapterPosition

           if (position != RecyclerView.NO_POSITION){
               listener.onItemClick(position)
           }
       }

       fun checked(){
           taskCheckBox.setOnClickListener{
               if (taskCheckBox.isChecked){
                   taskName.apply {
                       paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                   }
               }
               if (!taskCheckBox.isChecked){
                   taskName.paintFlags = Paint.ANTI_ALIAS_FLAG
               }

           }
       }

        fun bindData(task: Task){
            taskName.text = task.name
            taskCheckBox.isChecked = task.isSelected
        }
    }

    inner class SwipeToDeleteCallback: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = false

        // the function to carry out swiping on viewholder position
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            if (viewHolder is TaskViewHolder){
                makeMovementFlags(
                    ItemTouchHelper.ACTION_STATE_IDLE,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                ) or makeMovementFlags(
                    ItemTouchHelper.ACTION_STATE_SWIPE,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                )
            }
            else {0}

            return super.getMovementFlags(recyclerView, viewHolder)
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            deleteTaskItem(position)
            notifyItemRemoved(position)
        }


    }

}