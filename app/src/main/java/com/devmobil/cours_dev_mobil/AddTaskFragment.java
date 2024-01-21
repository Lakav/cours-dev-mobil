package com.devmobil.cours_dev_mobil;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;
import java.lang.ref.WeakReference;

public class AddTaskFragment extends Fragment {

    private EditText taskEditText;
    private Button addButton;
    private WeakReference<Context> contextWeakReference;
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

        // Utilisez une WeakReference pour éviter les fuites de mémoire
        contextWeakReference = new WeakReference<>(requireContext());

        taskEditText = view.findViewById(R.id.editTextTask);
        addButton = view.findViewById(R.id.buttonAddTask);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClicked();
            }
        });

        return view;
    }

    private void onAddButtonClicked() {
        Log.d("AddTaskFragment", "onAddButtonClicked called");
        String taskDescription = taskEditText.getText().toString();
        Context context = contextWeakReference.get();

        if (context != null && !taskDescription.isEmpty()) {
            // Créez une instance de TaskEntity avec la description de la tâche
            TaskEntity taskEntity = new TaskEntity(taskDescription);

            // Utilisez AsyncTask pour ajouter la tâche à la base de données
            new InsertTaskAsyncTask(appDatabase, context).execute(taskEntity);

            // Réinitialisez le champ de texte après l'ajout
            taskEditText.setText("");

            // Actualisez la liste des tâches dans MainActivity
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).refreshTasks();
            }
        } else {
            // Affichez un message d'erreur si le champ est vide
            Toast.makeText(context, "Please enter a task description", Toast.LENGTH_SHORT).show();
        }
    }

    private static class InsertTaskAsyncTask extends AsyncTask<TaskEntity, Void, Void> {
        private final AppDatabase appDatabase;
        private final WeakReference<Context> contextWeakReference;

        InsertTaskAsyncTask(AppDatabase appDatabase, Context context) {
            this.appDatabase = appDatabase;
            this.contextWeakReference = new WeakReference<>(context);
        }

        @Override
        protected Void doInBackground(TaskEntity... tasks) {
            // Utilisez votre DAO pour ajouter la tâche à la base de données
            appDatabase.taskDao().insertTask(tasks[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Context context = contextWeakReference.get();
            if (context != null) {
                // Faites quelque chose après l'insertion, si nécessaire
            }
        }
    }
}
