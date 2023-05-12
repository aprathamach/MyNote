package com.example.mynote;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteVModel extends AndroidViewModel {
    private NoteRepo noteRepo;
     private LiveData<List<MNote>> noteList;
    public NoteVModel(@NonNull Application application) {
        super(application);
        noteRepo = new NoteRepo(application);
        noteList = noteRepo.showAllData();
    }

    public void insert(MNote mNote){
        noteRepo.insertData(mNote);
    }public void delete(MNote mNote) {
        noteRepo.deleteData(mNote);
    }public void update(MNote mNote) {
        noteRepo.updateData(mNote);
    }


    public LiveData<List<MNote>> getAllNotes() {
        return noteList;
    }
}
