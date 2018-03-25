package com.example.android.nadiardhian.nadiaardhianie_1202154296_modul5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Setting extends AppCompatActivity {
    int wrn; //deklarasi warna
    TextView color; //deklarasi text view yang menyimpan nama warna
    AlertDialog.Builder alert; //deklarasi alert
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting); //layout yang digunakan

        alert = new AlertDialog.Builder(this);

        SharedPreferences shp = getApplicationContext().getSharedPreferences("shp", 0);
        edit = shp.edit(); //edit warna
        color = findViewById(R.id.warna); //memilih warna get id warna
        wrn = shp.getInt("background", R.color.white); //background awal yang ditampilkan dengan warna putih

        color.setText(getWarna(wrn)); //warna ditampilkan
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //jika menu di klik
        if (item.getItemId() ==android.R.id.home){ //menampilkan halaman awal
            this.onBackPressed();
        }
        return true;
    }

    @Override
    public void onBackPressed() { //jika menu kembali di klik
        startActivity(new Intent(Setting.this, MainActivity.class)); //intent ke kelas main activity
        finish();
    }

    public String getWarna(int i){ //get warna
        if (i==R.color.blue){ //jika warna biru di klik
            return "Blue"; //warna biru ditampilkan
        }else if (i==R.color.red){ //jika warna merah di klik
            return "Red";//warna merah di tampilkan
        }else if (i==R.color.green){ //jika warna hijau di klik
            return "Green"; //warna hijau ditampilkan
        }else if (i==R.color.violet){ //jika warna ungu di klik
            return "Violet";//warna ungu ditampilkan
        }else{
            return "White"; //akan menampilkan warna putih
        }
    }
    public int getIntColor(int i){ //get color
        if (i==R.color.blue){ //jika warna biru dipilih
            return R.id.btn_blue; //get id button blue
        }else if (i==R.color.red){ //jika warna merah dipilih
            return R.id.btn_red; //get id button red
        }else if (i==R.color.green){ //jika warna hijau dipilih
            return R.id.btn_green; //get id button green
        }else if (i==R.color.violet){ //jika warna ungu dipilih
            return R.id.btn_violet; //get id button violet
        }else {
            return R.id.btn_white; ////get id button white
        }
    }

    public void pencet(View view) { //jika tombol pencet di klik
        alert.setTitle("Choose Color"); //akan ada alert "choose color"
        View v = getLayoutInflater().inflate(R.layout.pilihanwarna,null); //akan menampilkan layout ini
        alert.setView(v);

        final RadioGroup rg = v.findViewById(R.id.rg); //get id radio grup
        rg.check(getIntColor(wrn));//get color

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) { //alert posiif
                int cek = rg.getCheckedRadioButtonId();
                switch (cek){ //kondisi untuk memilih warna
                    case R.id.btn_blue: //id button blue di get
                        wrn = R.color.blue; //menampilkan warna blue
                        break;
                    case R.id.btn_green: //id button green di get
                        wrn = R.color.green; //menampilkan warna green
                        break;
                    case R.id.btn_red: //id button red di get
                        wrn = R.color.red; //menampilkan warna red
                        break;
                    case R.id.btn_white: //id button white di get
                        wrn = R.color.white; //menampilkan warna white
                        break;
                    case R.id.btn_violet: //id button violet di get
                        wrn = R.color.violet; //menampilkan warna violet
                        break;
                }
                color.setText(getWarna(wrn)); //menampilkan nama warna di text
                edit.putInt("background", wrn);//background akan berubah warnanya
                edit.commit();
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) { //jika di cancel
                dialogInterface.dismiss();//menutup dialog
            }
        });alert.create().show();
    }
}
