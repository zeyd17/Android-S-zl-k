package com.zeyd.sozluk;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity implements View.OnClickListener{
    Button btnEkle;
    ListView list;
    EditText txtTr,txtING;
    ArrayList<Kelime> kelimeler;
    KelimeAdapter adapter;
    int ID=-1;
    DB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    void  init(){
        db =new DB(this);

        btnEkle = (Button) findViewById(R.id.btnFoy2);
        btnEkle.setOnClickListener(this);

        txtING = (EditText) findViewById(R.id.ing);
        txtTr = (EditText) findViewById(R.id.tr);

        kelimeler =new ArrayList<Kelime>();


        list = (ListView) findViewById(R.id.liste);
        registerForContextMenu(list);
        adapter =new KelimeAdapter(this,kelimeler);
        list.setAdapter(adapter);
        Listele();
    }

    @Override
    public void onClick(View v) {
        if(v == btnEkle)
        {
            if(ID == -1)
            {
                Ekle(txtTr.getText().toString(),txtING.getText().toString());
            }else{
                Guncelle(ID,txtTr.getText().toString(),txtING.getText().toString());
                ID =-1;
                btnEkle.setText("Ekle");
            }
            txtING.setText("");
            txtTr.setText("");
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int id =kelimeler.get(info.position).getId();
        if(item.getItemId() == R.id.Sil)
        {
            Sil(id);
        }else  if(item.getItemId() == R.id.Guncelle)
        {
            ID =id;
            KayitOku(id);
            btnEkle.setText("Güncelle");

        }
        return super.onContextItemSelected(item);
    }

    void  KayitOku(int id)
    {
        SQLiteDatabase d =db.getReadableDatabase();
        Cursor c =d.rawQuery("Select * from sozluk where id="+id,null);
        if(c.moveToFirst())
        {
            String tr =c.getString(c.getColumnIndex("tr"));
            String ing =c.getString(c.getColumnIndex("ing"));
            txtING.setText(ing);
            txtTr.setText(tr);
        }
    }

    void  Ekle(String tr, String ing)
    {
        SQLiteDatabase d =db.getWritableDatabase();
        d.execSQL("insert into sozluk(tr,ing) values('"+tr+"','"+ing+"')");
        Toast.makeText(this,"Eklendi",Toast.LENGTH_LONG).show();
        Listele();
    }

    void  Guncelle(int id ,String tr,String ing)
    {
        SQLiteDatabase d =db.getWritableDatabase();
        d.execSQL("update sozluk set tr='"+tr+"',ing='"+ing+"' where id="+id);
        Toast.makeText(this,"Güncellendi",Toast.LENGTH_LONG).show();
        Listele();
    }

    void  Sil(int id)
    {
        SQLiteDatabase d =db.getWritableDatabase();
        d.execSQL("delete from sozluk where id="+id);
        Toast.makeText(this,"Silindi",Toast.LENGTH_LONG).show();
        Listele();
    }

    void  Listele()
    {
        SQLiteDatabase d =db.getReadableDatabase();
        Cursor c =d.rawQuery("Select * from sozluk",null);
        if(c.moveToFirst())
        {kelimeler.clear();
            do {
                int id=0;
                String tr="";
                String ing ="";
                id =c.getInt(c.getColumnIndex("id"));
                tr =c.getString(c.getColumnIndex("tr"));
                ing =c.getString(c.getColumnIndex("ing"));
                Kelime k =new Kelime(id,tr,ing);
                kelimeler.add(k);
            }while (c.moveToNext());
            adapter.notifyDataSetChanged();
        }
    }
}
