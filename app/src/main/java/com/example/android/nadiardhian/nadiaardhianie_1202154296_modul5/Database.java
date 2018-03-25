package com.example.android.nadiardhian.nadiaardhianie_1202154296_modul5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by user on 3/25/2018.
 */

public class Database extends SQLiteOpenHelper {
    Context context; //deklarasi context
    SQLiteDatabase data; //deklarasi db dengan sql
    public static final String nama_db = "Modul5.db";//deklarasi nama db
    public static final String nama_table = "Todo"; //deklarasi nama table
    public static final String kolom1 = "judul"; //deklarasi nama kolom(judul)
    public static final String kolom2 = "deskripsi"; //deklarasi nama kolom (deskripsi)
    public static final String kolom3 = "priority"; //deklarasi nama kolom (priority)

    public Database(Context context) { //method ini digunakan untuk membuat database
        super(context, nama_db, null, 1); //dengan nama db tidak boleh kosong
        this.context = context;
        this.data = this.getWritableDatabase();
        data.execSQL("create table if not exists "+nama_table+" (judul varchar(50) primary key, deskripsi varchar(50), priority varchar(50)) "); //merupakan ketentuan database
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { //membuat sqlite database
        sqLiteDatabase.execSQL("create table if not exists "+nama_table+" (judul varchar(50) primary key, deskripsi varchar(50), priority varchar(50)) ");//membuat nama tabel dan kolom kolomnya
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { //method ini dipanggil ketika database akan di upgrade
        sqLiteDatabase.execSQL("drop table if exists "+nama_table); //jika ada yang dihapus
        onCreate(sqLiteDatabase);
    }
    public boolean inputdata (itemTodo item){ //jika data akan di masukkan ke dalam database
        ContentValues cv = new ContentValues(); //menggunakan content provider dengan variable cv
        cv.put(kolom1, item.getTodo()); //cv kolom1 get data nama todo
        cv.put(kolom2, item.getDesc()); //cv kolom2 get data desc
        cv.put(kolom3, item.getPrior()); //cv kolom3 get data prior
        long hasil = data.insert(nama_table, null, cv); //hasil nya akan dimasukan ke dalam nama kolom yang sesuai dan nama table nya
        if (hasil==-1){ //jika tidak ada inputan
            return false;
        }else {
            return true;
        }
    }

    public boolean hapusdata (String nama){ //jika data di hapus
        return data.delete(nama_table, kolom1+"=\""+nama+"\"", null)>0; //akan menghapus data di dalam kolom sesuai dengan nama tabel nya
    }
    public void getAllItem (ArrayList<itemTodo> list){ //get semua data dengan array
        Cursor cursor = this.getReadableDatabase().rawQuery("select judul, deskripsi, priority from "+nama_table, null); //menampilkan judul kolom dan nama table
        while (cursor.moveToNext()){
            list.add(new itemTodo(cursor.getString(0), cursor.getString(1), cursor.getString(2))); //menambahkan data baru
        }
    }
    public void clearTable() { //jika table di hapus
        data.execSQL("delete from "+nama_table);//data terhapus
    }
}
