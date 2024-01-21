package com.devmobil.cours_dev_mobil;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<TaskEntity> tasks;

    public TaskAdapter(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);

        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        TaskEntity currentTask = tasks.get(position);

        // Assurez-vous que le champ textViewTitle affiche correctement la description
        holder.textViewTitle.setText(currentTask.getDescription());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
        }
    }
}
