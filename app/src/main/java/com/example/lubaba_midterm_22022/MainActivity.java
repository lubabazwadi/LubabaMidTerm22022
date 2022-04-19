package com.example.lubaba_midterm_22022;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int count=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addbttn=(Button)findViewById(R.id.bttnAdd);
        EditText id=(EditText)findViewById(R.id.editTextID);
        EditText name=(EditText)findViewById(R.id.editTextName);
        EditText email=(EditText)findViewById(R.id.editTextEmail);
        EditText phone=(EditText)findViewById(R.id.editTextPhone);

        final DatabaseHelper MyDB = new DatabaseHelper(this);

        addbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String id_val = id.getText().toString();
                    String name_val = name.getText().toString();
                    String email_val = email.getText().toString();
                    String phone_val = phone.getText().toString();

                    MyDB.addData(id_val, name_val, email_val, phone_val);
                    Log.d("Leena","after adding value");

                    Toast.makeText(MainActivity.this, "Successful Add", Toast.LENGTH_LONG).show();
                }
                catch (Exception e){
                    count=1;
                    Toast.makeText(MainActivity.this,"Unsuccessful Add please insert all necessary data",Toast.LENGTH_LONG).show();
                }
            }

        });


        final Button mainButton3 = (Button) findViewById(R.id.activity3);
        final Button mainButton4 = (Button) findViewById(R.id.activity4);

        mainButton3.setOnClickListener(e-> startActivity(new Intent(MainActivity.this,Splash.class)));
        mainButton4.setOnClickListener(e-> startActivity(new Intent(MainActivity.this,MainActivity2.class)));
    }}
