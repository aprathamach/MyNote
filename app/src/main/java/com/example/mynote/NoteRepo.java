package com.example.mynote;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepo {
    private NoteDao noteDao;
    private LiveData<List<MNote>> noteList;

    public NoteRepo(Application application)
    {
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        noteList= noteDao.showAllData();
    }
    public void insertData (MNote note){new InsertTask(noteDao).execute(note);}
    public void updateData (MNote note){new UpdateTask(noteDao).execute(note);}
    public void deleteData (MNote note){new DeleteTask(noteDao).execute(note);}
    public LiveData<List<MNote>> showAllData ()
    {
        return noteList;
    }
    private static class InsertTask extends AsyncTask<MNote,Void,Void>{
    private NoteDao noteDao;

        public InsertTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(MNote... mNotes) {
            noteDao.insert(mNotes[0]);
            return null;
        }
    }
    private static class DeleteTask extends AsyncTask<MNote,Void,Void>{
        private NoteDao noteDao;

        public DeleteTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(MNote... mNotes) {
            noteDao.delete(mNotes[0]);
            return null;
        }
    }
    private static class UpdateTask extends AsyncTask<MNote,Void,Void>{
        private NoteDao noteDao;

        public UpdateTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(MNote... mNotes) {
            noteDao.update(mNotes[0]);
            return null;
        }
    }
}
