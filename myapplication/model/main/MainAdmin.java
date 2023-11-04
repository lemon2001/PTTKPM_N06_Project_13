package com.example.myapplication.model.main;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.DangBai;
import com.example.myapplication.R;
import com.example.myapplication.adapter.adapterTintuc;
import com.example.myapplication.database.database;
import com.example.myapplication.model.Tin;

import java.util.ArrayList;

public class MainAdmin extends AppCompatActivity {

    RecyclerView listView;
    Button buttonThem;

    ArrayList<Tin> tinArrayList;
    adapterTintuc adaptertruyen;
    database databaseDocTruyen;
    ArrayList<Tin> tinArraylist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        listView = findViewById(R.id.listviewAdmin);
        buttonThem = findViewById(R.id.buttonAddTin);

        initList();
      /*  listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DialogDelete(position);
                return false;
            }


        });*/


        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                int id = intent.getIntExtra("Id",0);
                Intent intent1 = new Intent(MainAdmin.this, DangBai.class);
                intent.putExtra("Id",id);
                startActivity(intent1);
            }
        });


    }


    //Gán DL vào listview
    public void initList(){
        tinArrayList = new ArrayList<>();
        databaseDocTruyen = new database(this);

        Cursor cursor1 = databaseDocTruyen.getData2();

        while (cursor1.moveToNext()){

            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);
            tinArrayList.add(new Tin(id,tentruyen,noidung,anh,id_tk));

        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        listView.setLayoutManager(linearLayoutManager);

        adaptertruyen=new adapterTintuc(getApplicationContext(), tinArraylist);
        listView.setAdapter(adaptertruyen);
        cursor1.moveToFirst();
        cursor1.close();
    }
}