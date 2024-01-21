package com.devmobil.cours_dev_mobil;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface TaskDao {

    @Query("SELECT * FROM tasks")
    List<TaskEntity> getAllTasks();

    @Insert
    void insertTask(TaskEntity task);

    @Insert
    void insert(TaskEntity task);

    @Update
    void update(TaskEntity task);

    @Delete
    void delete(TaskEntity task);

}
