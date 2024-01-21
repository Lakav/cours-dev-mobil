package com.devmobil.cours_dev_mobil;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private EditText editText;
    private CalendarView calendarView;
    private Button button;

    private MaBaseDeDonneesHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialiser les éléments de l'interface utilisateur
        editText = findViewById(R.id.editTextText);
        calendarView = findViewById(R.id.calendarView2);
        button = findViewById(R.id.button);

        // Initialiser la base de données
        dbHelper = new MaBaseDeDonneesHelper(this);

        // Ajouter un écouteur de clic sur le bouton
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Récupérer les données des éléments de l'interface utilisateur
                String name = editText.getText().toString();
                long selectedDate = calendarView.getDate();

                // Ajouter les données à la base de données
                ajouterDonneesDansBDD(name, selectedDate);
            }
        });
    }

    private void ajouterDonneesDansBDD(String name, long date) {
        // Obtenir une instance de la base de données en écriture
        SQLiteDatabase maBaseDeDonnees = dbHelper.getWritableDatabase();

        // Préparer les données à insérer
        ContentValues values = new ContentValues();
        values.put("nom", name);
        values.put("date", date);

        // Insérer les données dans la base de données
        long newRowId = maBaseDeDonnees.insert("MaTable", null, values);

        // Fermer la base de données
        maBaseDeDonnees.close();
    }
}
