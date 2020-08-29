package com.example.eone.mainLayouts.Todo.addTodo;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.eone.R;
import com.example.eone.database.AppDatabase;
import com.example.eone.database.DBRepository;
import com.example.eone.database.TodoEntity;

import java.util.ArrayList;
import java.util.List;

public class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoRecyclerAdapter.TodoHolder> {
    List<TodoEntity> allTodos = new ArrayList<>();
    Context context;
    DBRepository dbRepository;
    AppDatabase db;
    private OnItemLongClickListener listener1;
    private OnItemClickListener listener;

    public TodoRecyclerAdapter(Context context, DBRepository dbRepository) {
        this.context = context;
        this.dbRepository = dbRepository;
    }

    public class TodoHolder extends RecyclerView.ViewHolder {
        TextView title,desc,date,time,priority;
        View mView;
        public TodoHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.titleRecycler);
            desc = (TextView)itemView.findViewById(R.id.descRecycler);
            date = (TextView)itemView.findViewById(R.id.dateRecycler);
            time = (TextView)itemView.findViewById(R.id.timeRecycler);
            priority = (TextView)itemView.findViewById(R.id.priorityRecycler);
            mView =itemView;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.OnItemClick(allTodos.get(position));
                    }

                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = getAdapterPosition();
                    if (listener1 != null && position != RecyclerView.NO_POSITION) {
                        listener1.OnItemLongClick(allTodos.get(position));
                    }
                    return false;
                }
            });
        }
    }
    @NonNull
    @Override
    public TodoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_layout_list, parent, false);
        return new TodoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoHolder holder, int position) {
        TodoEntity todoEntity = allTodos.get(position);
        holder.title.setText(todoEntity.getTodoTitle());
        holder.desc.setText(todoEntity.getTodoDesc());
        holder.date.setText(todoEntity.getTodoDate());
        holder.time.setText(todoEntity.getTodoTime());
        holder.priority.setText(todoEntity.getTodoPriority());
        db = Room.databaseBuilder(context,AppDatabase.class,"Brand").allowMainThreadQueries().build();
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Delete Artisian")
                        .setMessage("Are you sure you want to Delete this Artisian?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {


                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.todoDao().deleteByTodoId(todoEntity.getTodoId());
                                allTodos.remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());

                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return allTodos.size();
    }
    public void setTodos(List<TodoEntity> allTodos) {
        this.allTodos = allTodos;
        notifyDataSetChanged();
    }

    public TodoEntity getTodoAt(int position) {
        return allTodos.get(position);
    }

    public interface OnItemClickListener {
        void OnItemClick(TodoEntity todoEntity);
    }
    public interface OnItemLongClickListener {
        void OnItemLongClick(TodoEntity todoEntity);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.listener1 = listener;
    }
    public void clear(){
        allTodos.clear();
    }
}
