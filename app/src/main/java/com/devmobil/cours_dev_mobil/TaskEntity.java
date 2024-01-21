package com.devmobil.cours_dev_mobil;

import androidx.room.ColumnInfo;
        import androidx.room.Entity;
        import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class TaskEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    private String taskName;
    private String taskDate;

    // Ajoutez une méthode getter pour l'ID
    public int getId() {
        return id;
    }

    // Ajoutez une méthode setter pour l'ID si nécessaire
    public void setId(int id) {
        this.id = id;
    }

    // Ajoutez une méthode getter pour le titre
    public String getTitle() {
        return title;
    }

    // Ajoutez une méthode setter pour le titre si nécessaire
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    // Getter et Setter pour taskDate
    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

}
