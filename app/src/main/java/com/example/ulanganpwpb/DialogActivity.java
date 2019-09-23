package com.example.ulanganpwpb;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DialogActivity extends DialogFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dialog, container, false);
        if(getDialog() != null && getDialog().getWindow() !=null){
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        }
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater =getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dialog,null);

        Button update =view.findViewById(R.id.updatedata);
        update.setOnClickListener(this);
        Button hapus = view.findViewById(R.id.hapusdata);
        hapus.setOnClickListener(this);

        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.updatedata:
                Intent intent = new Intent(getContext(),UpdateData.class);
                Bundle bundle = getArguments();
                intent.putExtra("id",bundle.getInt("id"));
                intent.putExtra("judul",bundle.getString("judul"));
                intent.putExtra("desc",bundle.getString("desc"));
                dismiss();
                startActivity(intent);

                break;
            case R.id.hapusdata:
                DatabaseHelper db = new DatabaseHelper(getContext());
                bundle = getArguments();
                db.delete(bundle.getInt("id"));

                Toast.makeText(getContext(),"Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                dismiss();
        }
    }

}
