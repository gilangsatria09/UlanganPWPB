package com.example.ulanganpwpb;

import android.app.Application;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements RecyclerViewAdapter.onUserClickListener {

    List<Note> noteList =new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView btnAdd = findViewById(R.id.btnTambah);
        ImageView btnRefresh = findViewById(R.id.refresh);



        initData();

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               initData();
               Toast.makeText(getApplicationContext(),"Refreshed",Toast.LENGTH_SHORT);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,TambahActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        noteList = db.selectNote();
        RecyclerViewAdapter rvAdapter = new RecyclerViewAdapter(getApplicationContext(),noteList,this);
        recyclerView.setAdapter(rvAdapter);
        rvAdapter.notifyDataSetChanged();
    }


    public void initData(){
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        noteList = db.selectNote();
        RecyclerViewAdapter rvAdapter = new RecyclerViewAdapter(getApplicationContext(),noteList,this);
        recyclerView.setAdapter(rvAdapter);
        rvAdapter.notifyDataSetChanged();
    }

    public void dialog(int id,String tanggal,String judul,String desc){
        DialogActivity Dialog = new DialogActivity();
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        bundle.putString("tanggal",tanggal);
        bundle.putString("judul",judul);
        bundle.putString("desc",desc);
        if (Dialog.getDialog() != null && Dialog.getDialog().getWindow() !=null){
            Dialog.getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Dialog.getDialog().getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        }
        Dialog.setArguments(bundle);
        Dialog.show(getSupportFragmentManager(), "DIALOG");
    }

    @Override
    public void onUserClick(int id,String tanggal,String judul,String desc){
        dialog(id,tanggal,judul,desc);
    }

}
