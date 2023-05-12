package com.example.mynote;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = MNote.class, version = 5)
@TypeConverters({DateConverter.class, TimeConverter.class})

public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;

    public abstract NoteDao noteDao();
    public static synchronized NoteDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext()
                    ,NoteDatabase.class, "note_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
