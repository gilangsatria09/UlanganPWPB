package com.example.ulanganpwpb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.UserViewHolder> {

    Context context;
    onUserClickListener listener;
    List<Note> noteInfo;

    public RecyclerViewAdapter(Context context, List<Note> noteInfo, onUserClickListener listener){
        this.context = context;
        this.noteInfo = noteInfo;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_recycler_view,viewGroup,false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    public interface onUserClickListener{
        void onUserClick(int id,String tanggal,String judul,String desc);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        final Note note = noteInfo.get(i);
        userViewHolder.tanggal.setText(note.getTanggal());
        userViewHolder.judul.setText(note.getJudul());
        userViewHolder.desc.setText(note.getDeskripsi());
        userViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUserClick(note.getNo(),note.getTanggal(),note.getJudul(),note.getDeskripsi());
            }
        });

    }

    @Override
    public int getItemCount() {
        return noteInfo.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tanggal,judul,desc;
        CardView cardView;

        public UserViewHolder(@NonNull View itemView){
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            tanggal = itemView.findViewById(R.id.tvTanggal);
            judul = itemView.findViewById(R.id.tvJudul);
            desc = itemView.findViewById(R.id.tvKonten);
        }

    }
}
