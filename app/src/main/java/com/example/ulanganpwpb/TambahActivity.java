package com.example.ulanganpwpb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TambahActivity extends AppCompatActivity {

    EditText judul,desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        judul = findViewById(R.id.etJudul);
        desc = findViewById(R.id.etDeskripsi);
        Button btnTambah = findViewById(R.id.btnTambahData);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                finish();
            }
        });

    }

    public void insertData(){
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        Note note = new Note();

        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        note.setTanggal(date);
        note.setJudul(judul.getText().toString());
        note.setDeskripsi(desc.getText().toString());

        db.insertData(note);
        Toast.makeText(getApplicationContext(),"Data Berhasil Dimasukkan",Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(i);

    }
}
