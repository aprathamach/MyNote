package com.example.mynote;

import androidx.room.Entity;
import androidx.room.PrimaryKey; //it is part of the room database-it's called anotation
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName= "my_note")
@TypeConverters({DateConverter.class, TimeConverter.class})
public class MNote {
    private String title;
    private String descr;
    private Date date;
    private Date time;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }



    @PrimaryKey(autoGenerate = true)
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() { //for it need to be used we need to make geeter
        return id;
    }

    public MNote(String title, String descr,Date date, Date time) { //making constrctor for title and description
        this.title = title;
        this.descr = descr;
        this.date = date;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
