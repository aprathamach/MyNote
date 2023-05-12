package com.example.mynote;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class signIn extends AppCompatActivity {

//    EditText mEmail, mpassword;
//    Button mLoginBtn;
//    TextView mCreateBtn;
//    ProgressBar progressBar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

    EditText txtUserName = findViewById(R.id.Email);
    EditText txtPassword = findViewById(R.id.password);
    Button btnLogin = findViewById(R.id.loginBtn);
    TextView txtRegister = findViewById(R.id.rButton);



    btnLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (txtUserName.getText().toString().equals("pratham") &&
                    txtPassword.getText().toString().equals("1996")) {
                // store login token into shared preferences
                SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
                SharedPreferences.Editor spEditor = sharedPreferences.edit();
                spEditor.putString("loginToken", "Loggedin");
                spEditor.commit();
                Intent intent = new Intent(signIn.this, MainActivity.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(signIn.this, "Invalid username or password", Toast.LENGTH_LONG).show();
            }
            if (TextUtils.isEmpty(txtUserName.getText().toString())){
                txtUserName.setError("EMAIL IS REQUIRED!!!");
                return;
            }

            if (TextUtils.isEmpty(txtPassword.getText().toString())){
                txtPassword.setError("PASSWORD IS REQUIRED!!!");
                return;
            }

            Toast.makeText(signIn.this, "SignupSuccesssfully", Toast.LENGTH_SHORT).show();
        }
    });
    txtRegister = findViewById(R.id.loginSignupBtn);
    txtRegister.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "TextView clicked", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),register.class));

        }
    });


      }
    @Override
    public void onBackPressed() {
        // do nothing, effectively disabling the back button
    }
}