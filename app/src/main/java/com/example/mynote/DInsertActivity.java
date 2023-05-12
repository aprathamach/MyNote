package com.example.mynote;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.mynote.databinding.ActivityDinsertBinding;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Locale;


public class DInsertActivity extends AppCompatActivity {
    ActivityDinsertBinding binding;

    TextView editDateText;

    int year;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDinsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        editDateText = findViewById(R.id.dateTextView);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


        TextView timeTextView = findViewById(R.id.timeSelect);
        timeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }

            private void showTimePickerDialog () {
                //Get the current time
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // TimePickerDialog shown
                TimePickerDialog timePickerDialog = new TimePickerDialog(DInsertActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Updated the textview with the selected time
                        String time = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
                        timeTextView.setText(time);

                    }
                }, hour, minute, true);
                timePickerDialog.show();
            }
        });




        String dateExtra = getIntent().getStringExtra("date");
        if (dateExtra != null && !dateExtra.isEmpty()) {
            editDateText.setText(dateExtra);
        }

        editDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DInsertActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" +year;
                        editDateText.setText(selectedDate);
                    }
                },year,month,day);
                datePickerDialog.show();

            }

        });




        String type= getIntent().getStringExtra("type");
        if(type.equals("update")) {
            setTitle("Update");
            binding.title.setText(getIntent().getStringExtra("title"));
            binding.descr.setText(getIntent().getStringExtra("descr"));
            binding.dateTextView.setText(getIntent().getStringExtra("date"));
            binding.timeSelect.setText(getIntent().getStringExtra("time"));
            int id = getIntent().getIntExtra("id", 0);
            binding.add.setText("Update Note");
            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("title", binding.title.getText().toString());
                    intent.putExtra("descr", binding.descr.getText().toString());
                    intent.putExtra("date", binding.dateTextView.getText().toString());
                    intent.putExtra("time", binding.timeSelect.getText().toString());
                    intent.putExtra("id", id);
                    setResult(RESULT_OK, intent);
                    finish();


                }
            });
        }else {

            setTitle("Add Mode");
            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("title", binding.title.getText().toString());
                    intent.putExtra("descr", binding.descr.getText().toString());
                    intent.putExtra("date",binding.dateTextView.getText().toString());
                    intent.putExtra("time", binding.timeSelect.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();

                }
            });
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DInsertActivity.this, MainActivity.class));
    }
}