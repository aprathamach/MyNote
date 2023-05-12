 package com.example.mynote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mynote.databinding.ActivityMainBinding;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

 public class MainActivity extends AppCompatActivity {
     ActivityMainBinding binding;
     private NoteVModel noteVModel;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);

         binding = ActivityMainBinding.inflate(getLayoutInflater());
         setContentView(binding.getRoot());

         noteVModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                 .get(NoteVModel.class);
         binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(MainActivity.this, DInsertActivity.class);
                 intent.putExtra("type", "Add More");
                 startActivityForResult(intent, 1);
             }
         });
         binding.Rv.setLayoutManager(new LinearLayoutManager(this));
         binding.Rv.setHasFixedSize(true);
         RvAdapter adapter = new RvAdapter();
         binding.Rv.setAdapter(adapter);

         noteVModel.getAllNotes().observe(this, new Observer<List<MNote>>() {
             @Override
             public void onChanged(List<MNote> mNotes) {
                 adapter.submitList(mNotes);
             }
         });
         new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
             @Override
             public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                 return false;
             }

             @Override
             public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                 if (direction == ItemTouchHelper.RIGHT) {
                     noteVModel.delete(adapter.getNote(viewHolder.getAdapterPosition()));
                     Toast.makeText(MainActivity.this, "Note Deleted", Toast.LENGTH_SHORT).show();

                 } else {
                     Intent intent = new Intent(MainActivity.this, DInsertActivity.class);
                     intent.putExtra("type", "update");
                     intent.putExtra("title", adapter.getNote(viewHolder.getAdapterPosition()).getTitle());
                     intent.putExtra("descr", adapter.getNote(viewHolder.getAdapterPosition()).getDescr());
                     intent.putExtra("date", adapter.getNote(viewHolder.getAdapterPosition()).getDate());
                     intent.putExtra("time", adapter.getNote(viewHolder.getAdapterPosition()).getTime());
                     intent.putExtra("id", adapter.getNote(viewHolder.getAdapterPosition()).getId());
                     startActivityForResult(intent, 2);
                 }

             }
         }).attachToRecyclerView(binding.Rv);
     }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
         super.onActivityResult(requestCode, resultCode, data);
         if(requestCode == 1)
         {
             String title = data.getStringExtra("title");
             String descr = data.getStringExtra("descr");
             String date = data.getStringExtra("date");
             DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
             Date todoDate = null;
             try{
                 todoDate = dateFormat.parse(date);
             }catch(ParseException e){
                throw new RuntimeException(e);
             }

             // Time

             String time = data.getStringExtra("time");
             DateFormat timeFormat = new SimpleDateFormat("HH:mm");
             Date todoTime = null;
             try {
                 todoTime = timeFormat.parse(time);
             } catch (ParseException e) {
                 throw new RuntimeException(e);
             }


             MNote mNote = new MNote(title,descr,todoDate,todoTime);
             noteVModel.insert(mNote);
             Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show();

         }
         else if(requestCode==2)
         {
//             String title = data.getStringExtra("title");
//             String descr = data.getStringExtra("descr");
//             String date = data.getStringExtra("date");
//             DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//             Date todoDate = null;
//             try{
//                 todoDate = dateFormat.parse(date);
//             }catch(ParseException e){
//                 throw new RuntimeException(e);
//             }
//             String time = data.getStringExtra("time");
//             DateFormat timeFormat = new SimpleDateFormat("HH:mm");
//             Date todoTime = null;
//             try {
//                 todoTime = timeFormat.parse(time);
//             } catch (ParseException e) {
//                 throw new RuntimeException(e);
//             }
//
//             MNote mNote = new MNote(title,descr,todoDate,todoTime);
////             mNote.setId(data.getIntExtra("id", 0));
//             noteVModel.update(mNote);
//             Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show();
             String title = data.getStringExtra("title");
             String descr = data.getStringExtra("descr");
             String date = data.getStringExtra("date");
             DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
             Date todoDate = null;
             try{
                 todoDate = dateFormat.parse(date);
             }catch(ParseException e){
                 throw new RuntimeException(e);
             }

             // Time

             String time = data.getStringExtra("time");
             DateFormat timeFormat = new SimpleDateFormat("HH:mm");
             Date todoTime = null;
             try {
                 todoTime = timeFormat.parse(time);
             } catch (ParseException e) {
                 throw new RuntimeException(e);
             }


             MNote mNote = new MNote(title,descr,todoDate,todoTime);
             noteVModel.insert(mNote);
             Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show();

         }
     }
     @Override
     public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
         MenuInflater inflater = getMenuInflater();
         inflater.inflate(R.menu.menu, menu);
         return true;

     }

     @Override
     public boolean onOptionsItemSelected(@NonNull MenuItem item) {

         switch (item.getItemId()) {
             case R.id.addCategory:
                 Intent intent = new Intent(MainActivity.this, signIn.class);
                 startActivity(intent);
                 return true;
             case R.id.menuLogout:
                 SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                 sharedPreferences.edit().clear().commit();
                 Intent intent1 = new Intent(this, signIn.class);
                 startActivity(intent1);
                 return true;

             default:
                 return super.onOptionsItemSelected(item);
         }

     }
     }
