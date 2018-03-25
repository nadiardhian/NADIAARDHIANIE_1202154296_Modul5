package com.example.android.nadiardhian.nadiaardhianie_1202154296_modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddTodo extends AppCompatActivity {
    EditText td, des, prior; //deklarasi edit text
    Database db; //deklarasi database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo); //layout yang digunakan
        td = findViewById(R.id.todo); //get id todo
        des = findViewById(R.id.desc); //get id desc
        prior = findViewById(R.id.prio); //get id prio
        db = new Database(this); //membuat database
    }

    @Override
    public void onBackPressed() { //jika tombol back di klik
        startActivity(new Intent(AddTodo.this, MainActivity.class)); //maka akan intent ke kelas main activity
        this.finish();
    }

    public void Add(View view) { //jika tombol add di klik
        if (db.inputdata(new itemTodo(td.getText().toString(), des.getText().toString(), prior.getText().toString()))){ //database akan menambahkan data dan akan di get dalam tipe data string
            Toast.makeText(this, "Todo added", Toast.LENGTH_SHORT).show(); //akan menampilkan toast ini jika berhasil ditambahkan
            onBackPressed();
        }else {
            Toast.makeText(this, "Todo failed to add", Toast.LENGTH_SHORT).show(); //akan menampilkan toast ini jika data gagal di tambahkan
            td.setText(null); //text todo kosong
            des.setText(null); //desc kosong
            prior.setText(null);// prior kosong
        }
    }
}
