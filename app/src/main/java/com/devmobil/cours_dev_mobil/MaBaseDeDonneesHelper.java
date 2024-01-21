package com.devmobil.cours_dev_mobil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MaBaseDeDonneesHelper extends SQLiteOpenHelper {
    private static final String NOM_BASE_DE_DONNEES = "Tp_mobile";
    private static final int VERSION_BASE_DE_DONNEES = 1;

    // Nom de la table
    private static final String TABLE_TACHE = "tache";

    // Noms des colonnes de la table
    private static final String COLONNE_ID = "_id"; // Si vous utilisez un ID auto-incrémenté
    private static final String COLONNE_NOM = "nom";
    private static final String COLONNE_DATE = "date";

    // Requête de création de la table
    private static final String CREER_TABLE_TACHE =
            "CREATE TABLE " + TABLE_TACHE + " (" +
                    COLONNE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLONNE_NOM + " TEXT," +
                    COLONNE_DATE + " INTEGER" +
                    ");";

    public MaBaseDeDonneesHelper(Context context) {
        super(context, NOM_BASE_DE_DONNEES, null, VERSION_BASE_DE_DONNEES);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Code de création de la base de données
        db.execSQL(CREER_TABLE_TACHE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Code de mise à niveau de la base de données en cas de changement de version
    }
}