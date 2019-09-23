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

public class UpdateData extends AppCompatActivity {

    EditText judul,desc;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        judul = findViewById(R.id.etJudul);
        desc = findViewById(R.id.etDeskripsi);
        Button btnEdit = findViewById(R.id.btnTambahData);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
                finish();
            }
        });

        initData();
    }

    public void initData(){
        Intent intent = getIntent();

        id = intent.getIntExtra("id",0);
        judul.setText(intent.getStringExtra("judul"));
        desc.setText(intent.getStringExtra("desc"));
    }
    public void updateData(){
        DatabaseHelper db =  new DatabaseHelper(getApplicationContext());
        Note note = new Note();

        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

        note.setNo(id);
        note.setJudul(judul.getText().toString());
        note.setTanggal(date);
        note.setDeskripsi(desc.getText().toString());

        db.update(note);
        Toast.makeText(getApplicationContext(),"Data Berhasil Diubah",Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(i);
    }
}
