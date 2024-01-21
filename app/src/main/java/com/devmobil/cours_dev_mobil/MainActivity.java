package com.devmobil.cours_dev_mobil;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
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
            // Vous pouvez ajouter ici le code pour traiter le clic du bouton
            // Par exemple, ajouter une tâche à la base de données
            // Ou effectuer une autre action en fonction de vos besoins
            textview.setText("Hello World");
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
