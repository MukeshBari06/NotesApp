package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.HashSet;


public class secondActivity extends AppCompatActivity {
    EditText editText;
    int Noteid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent=getIntent();
        Noteid=intent.getIntExtra("Noteid",-1);
        editText=findViewById(R.id.editTextSec);
        if(Noteid!=-1) {
            editText.setText(MainActivity.Notes.get(Noteid));
        }
        else {
            MainActivity.Notes.add("");
            Noteid=MainActivity.Notes.size()-1;
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.Notes.set(Noteid,String.valueOf(s));
                MainActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
                HashSet<String> set=new HashSet<>(MainActivity.Notes);
                sharedPreferences.edit().putStringSet("Notesset",set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }



    //MENU

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menusec,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.back){
            finish();
            return true;
        }
        return false;
    }
}
