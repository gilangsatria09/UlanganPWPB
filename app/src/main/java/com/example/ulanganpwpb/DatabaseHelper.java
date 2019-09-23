package com.example.ulanganpwpb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION=1;
    private static final String DB_NAME="db_note";
    private static final String TABLE_NAME="tbl_note";
    private static final String KEY_NUM = "id";
    private static final String KEY_TANGGAL="nama";
    private static final String KEY_JUDUL="judul";
    private static final String KEY_DESC="desc";

public DatabaseHelper(Context context){
    super(context,DB_NAME,null,DB_VERSION);
}


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableNote ="Create Table "+TABLE_NAME+"("+KEY_NUM+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+KEY_TANGGAL+" TEXT,"+KEY_JUDUL+" TEXT,"+KEY_DESC+" TEXT)";
        db.execSQL(createTableNote);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql =("Drop table if exists " + TABLE_NAME);
        db.execSQL(sql);
        onCreate(db);
    }

    public void insertData(Note note){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(KEY_TANGGAL,note.getTanggal());
        v.put(KEY_JUDUL,note.getJudul());
        v.put(KEY_DESC,note.getDeskripsi());
        db.insert(TABLE_NAME,null,v);
    }

    public List<Note> selectNote(){
        ArrayList<Note> nList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String columns[] = {KEY_NUM,KEY_TANGGAL,KEY_JUDUL,KEY_DESC};
        Cursor c = db.query(TABLE_NAME,columns,null,null,null,null,null);

        while (c.moveToNext()){
            int num = c.getInt(0);
            String tanggal = c.getString(1);
            String judul = c.getString(2);
            String desc = c.getString(3);

            Note n = new Note();
            n.setNo(num);
            n.setTanggal(tanggal);
            n.setJudul(judul);
            n.setDeskripsi(desc);
            nList.add(n);
        }
        return nList;
    }
    public void update(Note note){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues v = new ContentValues();

        v.put(KEY_TANGGAL,note.getTanggal());
        v.put(KEY_JUDUL,note.getJudul());
        v.put(KEY_DESC,note.getDeskripsi());

        String where = KEY_NUM+"='"+note.getNo()+"'";
        db.update(TABLE_NAME,v,where,null);
    }
    public void delete(int num){
        SQLiteDatabase db = getReadableDatabase();
        String where = KEY_NUM+"='"+num+"'";
        db.delete(TABLE_NAME,where,null);
    }

}
