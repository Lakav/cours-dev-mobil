package com.devmobil.cours_dev_mobil;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<TaskEntity> tasks;

    // Ajouter des listeners pour gérer les actions associées à chaque tâche
    private OnTaskClickListener onTaskClickListener;

    public interface OnTaskClickListener {
        void onModifyClick(int position);
        void onDeleteClick(int position);
        void onValidateClick(int position);
    }

    public void setOnTaskClickListener(OnTaskClickListener listener) {
        this.onTaskClickListener = listener;
    }

    // Constructeur pour initialiser la liste des tâches
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

        holder.textViewTitle.setText(currentTask.getTitle());

        // Utilisez holder.modifyButton, holder.deleteButton, holder.validateButton
        // pour accéder aux boutons dans votre élément de tâche
        // Ajoutez des listeners aux boutons pour gérer les actions
        // (modifier, supprimer, valider) associées à chaque tâche
        holder.modifyButton.setOnClickListener(v -> {
            if (onTaskClickListener != null) {
                onTaskClickListener.onModifyClick(position);
            }
        });

        holder.deleteButton.setOnClickListener(v -> {
            if (onTaskClickListener != null) {
                onTaskClickListener.onDeleteClick(position);
            }
        });

        holder.validateButton.setOnClickListener(v -> {
            if (onTaskClickListener != null) {
                onTaskClickListener.onValidateClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private Button modifyButton;
        private Button deleteButton;
        private Button validateButton;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            modifyButton = itemView.findViewById(R.id.modifyButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            validateButton = itemView.findViewById(R.id.validateButton);
        }
    }
}
