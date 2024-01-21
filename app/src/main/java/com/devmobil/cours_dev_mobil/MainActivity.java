package com.devmobil.cours_dev_mobil;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppDatabase appDatabase;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textview = findViewById(R.id.todolist);
        recyclerView = findViewById(R.id.todobox);
        FloatingActionButton button = findViewById(R.id.floatingActionButton);

        // Initialise la base de données
        appDatabase = AppDatabase.getDatabase(this);

        // Exécute la tâche asynchrone pour obtenir les tâches depuis la base de données
        new GetTasksAsyncTask().execute();

        button.setOnClickListener(v -> {
            // Remplace le contenu actuel par le fragment d'ajout de tâches
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new AddTaskFragment())
                    .addToBackStack(null)
                    .commit();
        });

    }

    // Tâche asynchrone pour obtenir les tâches depuis la base de données
    private class GetTasksAsyncTask extends AsyncTask<Void, Void, List<TaskEntity>> {
        @Override
        protected List<TaskEntity> doInBackground(Void... voids) {
            return appDatabase.taskDao().getAllTasks();
        }

        @Override
        protected void onPostExecute(List<TaskEntity> tasks) {
            // Initialise l'adaptateur et le RecyclerView avec les tâches récupérées
            TaskAdapter taskAdapter = new TaskAdapter(tasks);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            recyclerView.setAdapter(taskAdapter);
        }
    }

    // Méthode pour actualiser la liste des tâches depuis le fragment d'ajout de tâches
    public void refreshTasks() {
        new GetTasksAsyncTask().execute();
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("cybrary", "onStart: ");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("cybrary", "onResume: ");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("cybrary", "onPause: ");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("cybrary", "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("cybrary", "onDestroy: ");
    }
}
