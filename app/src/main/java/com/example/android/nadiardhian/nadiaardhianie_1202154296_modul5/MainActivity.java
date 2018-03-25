package com.example.android.nadiardhian.nadiaardhianie_1202154296_modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Database db; //deklarasi database untuk menampung data yang di input
    RecyclerView rv; //deklarasi recyclerview
    TodoAdapter adapter; //deklarasi adapter
    ArrayList<itemTodo> listitem; //deklarasi array dengan nama list item

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //layout yang digunakan
        rv = findViewById(R.id.rv_list); //get id recycler view
        listitem = new ArrayList<>(); //get array

        db = new Database(this); //membuat database dengan variable db
        db.getAllItem(listitem);//database akan get data dengan nama array listitem

        SharedPreferences shp = this.getApplicationContext().getSharedPreferences("shp", 0); //
        int warna = shp.getInt("background", R.color.white); //mendefinisikan warna untuk background

        adapter = new TodoAdapter(this, listitem, warna); //panggil adapter di kelas TodoAdapter yang dipanggil itu listitem dan warna
        rv.setHasFixedSize(true); //digunakan untuk membuat recyclerview mempertahankan ukuran yang sama
        rv.setLayoutManager(new LinearLayoutManager(this)); //layout yang digunakan recyclerview adalah layout manager
        rv.setAdapter(adapter); //set adapter

        geser();
    }
    public void geser() { //method jika list di geser
        ItemTouchHelper.SimpleCallback sc = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) { //menciptakan callback untuk drag yang diberikan dan swipe
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int posisi = viewHolder.getAdapterPosition();
                itemTodo now = adapter.getItem(posisi);

                if (direction==ItemTouchHelper.LEFT||direction==ItemTouchHelper.RIGHT){ //jika di swipe ke kanan atau kiri
                    if (db.hapusdata(now.getTodo())){ //data terhapus
                        adapter.removeitem(posisi); //data terhapus dari adapter
                    }
                }
            }
        };
        ItemTouchHelper helper =  new ItemTouchHelper(sc); //jika tidak menghapus data maka akan memanggil method callback diatas
        helper.attachToRecyclerView(rv); //data akan ditampilkan di recycler view
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //membuat menu opsi saat pengguna membuka menu untuk pertama kali
        getMenuInflater().inflate(R.menu.main, menu); //menampilkan menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //jika menu di klik
        int id = item.getItemId(); //get id item
        if (id==R.id.setting){ //jika id setting di klik
            startActivity(new Intent(MainActivity.this, Setting.class)); //maka akan pindah ke kelas setting
            finish();
        }
        return true;
    }

    public void tambah(View view) { //jika button tambah di klik
        startActivity(new Intent(MainActivity.this, AddTodo.class)); //setelah tombol di klik maka akan intent ke kelass addtodo
        finish();
    }
}
