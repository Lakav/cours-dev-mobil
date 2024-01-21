package com.devmobil.cours_dev_mobil;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTaskFragment extends Fragment {

    private EditText taskEditText;
    private Button addButton;
    private AppDatabase appDatabase;

    public AddTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);

        // Initialisez votre instance d'AppDatabase
        appDatabase = AppDatabase.getDatabase(requireContext());

        taskEditText = view.findViewById(R.id.editTextTask);
        addButton = view.findViewById(R.id.buttonAddTask);

        addButton.setOnClickListener(v -> onAddButtonClicked());

        return view;
    }

    private void onAddButtonClicked() {
        String taskDescription = taskEditText.getText().toString();

        if (!taskDescription.isEmpty()) {
            // Créez une instance de TaskEntity avec la description de la tâche
            TaskEntity taskEntity = new TaskEntity(taskDescription);

            // Utilisez votre DAO pour ajouter la tâche à la base de données
            appDatabase.taskDao().insertTask(taskEntity);

            // Réinitialisez le champ de texte après l'ajout
            taskEditText.setText("");

            // Actualisez la liste des tâches dans MainActivity
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).refreshTasks();
            }
        } else {
            // Affichez un message d'erreur si le champ est vide
            Toast.makeText(requireContext(), "Please enter a task description", Toast.LENGTH_SHORT).show();
        }
    }
}
