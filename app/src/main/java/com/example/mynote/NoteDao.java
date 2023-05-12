package com.example.mynote;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao

public interface NoteDao { //data access object(dao)
    @Insert
    public void insert (MNote note);

    @Update
    public void update (MNote note);

    @Delete
    public void delete (MNote note);

    @Query("Select * from my_note")
    public LiveData<List<MNote>> showAllData ();
}
